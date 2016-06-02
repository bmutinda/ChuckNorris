package com.bmutinda.chucknorris.managers;

import android.app.Application;

import com.bmutinda.httpbuster.Api;
import com.bmutinda.httpbuster.HttpBuster;

public class ChuckNorrisApp extends Application {

    static HttpBuster httpBuster;

    @Override
    public void onCreate(){
        super.onCreate();

        Api api = new Api();
        api.setEndpoint("http://api.icndb.com/");
        httpBuster = HttpBuster.withApi(api).build();
    }

    public static HttpBuster getHttpBuster(){
        return httpBuster;
    }
}
