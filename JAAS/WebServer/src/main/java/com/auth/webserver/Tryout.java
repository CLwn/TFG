/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth.webserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.logging.Log;

/**
 *
 * @author CLwn1
 */
public class Tryout {
    public static void send() throws IOException {
        URL url = new URL("/auth");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        conn.setRequestProperty("name", "marc");
        
        conn.connect();
    
        String paramsString = "hoal";//sbParams.toString();
    
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(paramsString);
        wr.flush();
        wr.close();
 
        //Reader in = new BufferedReader(new InputStreamReader(
          //      conn.getInputStream(), "UTF-8"));
        //for (int c = in.read(); c != -1; c = in.read())
        //    System.out.print((char) c);

//        InputStream in = conn.getInputStream();
//
//        InputStreamReader isw = new InputStreamReader(in);
//
//        int data = isw.read();
//        while (data != -1) {
//            char current = (char) data;
//            data = isw.read();
//            System.out.print(current);
//        }
    }
 
    public static void main(String[] args) {
        try {
            send();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
