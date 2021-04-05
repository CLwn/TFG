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
import android.webkit.URLUtil;


import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;


import java.io.IOException;

import java.io.PrintWriter;

import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    private CameraSource cameraSource;
    private SurfaceView cameraView;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private String token = "", tokenanterior = "";
    private Socket user;
    private PrintWriter show;
    private int port = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraView = (SurfaceView) findViewById(R.id.camera_view);
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
                        } else{
                            //comparteix en altres apps
                            //Intent shareIntent = new Intent();
                            //shareIntent.setAction(Intent.ACTION_SEND);
                            //shareIntent.putExtra(Intent.EXTRA_TEXT, token);
                            //shareIntent.setType("text/plain");
                            //startActivity(shareIntent);
                            try {
                                user = new Socket("192.168.1.114", 400);
                                show = new PrintWriter(user.getOutputStream());
                                show.write(token+";"+"marc");
                                show.flush();
                                user.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

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



}