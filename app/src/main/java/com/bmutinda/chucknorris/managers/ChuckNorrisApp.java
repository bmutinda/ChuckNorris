package com.bmutinda.chucknorris.managers;

import com.bmutinda.chucknorris.models.Category;
import com.bmutinda.chucknorris.models.Joke;
import com.bmutinda.httpbuster.Api;
import com.bmutinda.httpbuster.HttpBuster;
import com.orm.SugarApp;

public class ChuckNorrisApp extends SugarApp {

    static HttpBuster httpBuster;

    @Override
    public void onCreate(){
        super.onCreate();

        Api api = new Api();
        api.setEndpoint("http://api.icndb.com/");
        httpBuster = HttpBuster.withApi(api).build();

        Joke.deleteAll(Joke.class);
        Category.deleteAll(Category.class);
    }

    public static HttpBuster getHttpBuster(){
        return httpBuster;
    }
}
