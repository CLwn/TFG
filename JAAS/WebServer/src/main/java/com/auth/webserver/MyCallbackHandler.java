/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auth.webserver;

import java.io.IOException;
import java.util.Date;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 *
 * @author CLwn1
 */
public class MyCallbackHandler implements CallbackHandler{
    private String user;
    private Date date;

    public MyCallbackHandler(String user, Date date) {
        this.user = user;
        this.date = date;
    }
    

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                NameCallback nameCallback = (NameCallback) callback;
                nameCallback.setName(user);
            } // else {
            //throw new UnsupportedCallbackException(callbacks[i], "The submitted Callback is unsupported");
            //}   
        }
    }
    
}
