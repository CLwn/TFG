
package com.example.qr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.TextView;
import android.widget.Toast;


import com.example.qr.data.DataModal;
import com.example.qr.data.remote.RetrofitAPI;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;


import java.io.IOException;

import java.io.PrintWriter;

import java.net.Socket;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private CameraSource cameraSource;
    private SurfaceView cameraView;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private String token = "", tokenanterior = "";
    private Socket user;
    private PrintWriter show;
    private int port = 0;
    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraView = (SurfaceView) findViewById(R.id.camera_view);
        test = findViewById(R.id.test);
        initQR();
    }

    public void initQR(){

        // creo el detector qr
        BarcodeDetector barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.ALL_FORMATS).build();

        //creo la càmera
        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1600,1024)
                .setAutoFocusEnabled(true).build();

        //listener del cicle de vida de la càmera
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                    //verifico si l'usuari ha donat els persmisos per la càmera
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED){

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        //verifiquem la versió de android que sigui almenys la M per mostrar
                        // el dialeg de la solicitud de la càmera
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA));
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                    return;
                } else {
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch ( IOException e){
                        Log.e("Camera Source", e.getMessage());
                    }
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                cameraSource.stop();
            }
        });

        //preparo el detector de QR
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() > 0) {

                    //obtenim el token
                    token = barcodes.valueAt(0).displayValue;

                    //verifiquem que el token anterior no sigui igual al actual
                    //això es útil per evitar múltiples trucades empleant el mateix token
                    if (!token.equals(tokenanterior)) {

                        //guardem l'últim token procesat
                        tokenanterior = token;
                        Log.i("token", token);

                        if (URLUtil.isValidUrl(token)){
                            //si es una url vàlida obre el navegador
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(token));
                            startActivity(browserIntent);
                        } else {

                            postData("marc");

                        }

                            new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    synchronized (this){
                                        wait(5000);
                                        //netegem el token
                                        tokenanterior = "";
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        }).start();
                    }
                }
            }
        });
    }

    private void postData(String name) {

        // below line is for displaying our progress bar.
        //loadingPB.setVisibility(View.VISIBLE);

        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://192.168.1.114:8080/WebServer/webresources/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // passing data from our text fields to our modal class.
        DataModal modal = new DataModal(name);

        // calling a method to create a post and passing our modal class.
        Call<DataModal> call = retrofitAPI.createPost(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {
                // this method is called when we get response from our api.
                Toast.makeText(MainActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                // below line is for hiding our progress bar.
                //loadingPB.setVisibility(View.GONE);

                // on below line we are setting empty text
                // to our both edit text.
                //jobEdt.setText("");
                //nameEdt.setText("");

                // we are getting response from our body
                // and passing it to our modal class.
                DataModal responseFromAPI = response.body();

                // on below line we are getting our data from modal class and adding it to our string.
                String responseString = "Response Code : " + response.code() + "\nName : " + responseFromAPI.getUsername();

                // below line we are setting our
                // string to our text view.
                test.setText(responseString);
            }

            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                test.setText("Error found is : " + t.getMessage());
            }
        });

    }
}