package com.bmutinda.chucknorris.network;

import com.bmutinda.chucknorris.callbacks.ListFetchCallback;
import com.bmutinda.chucknorris.managers.ChuckNorrisApp;
import com.bmutinda.chucknorris.managers.data.jokes.JokesDataSource;
import com.bmutinda.chucknorris.models.Category;
import com.bmutinda.chucknorris.models.Joke;
import com.bmutinda.chucknorris.utils.AppLogger;
import com.bmutinda.httpbuster.ApiCallback;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JokesRemoteSource implements JokesDataSource {

    @Override
    public void getAll(final ListFetchCallback<Joke> listFetchCallback) {
        getAll("jokes", null, listFetchCallback);
    }

    @Override
    public void getAll(ListFetchCallback<Joke> listFetchCallback, Category category) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("limitTo", String.format("[%s]", category.getName()));
        getAll("jokes", map, listFetchCallback);
    }

    private void getAll(String url, HashMap<String, Object> map, final ListFetchCallback<Joke> listFetchCallback){
        if ( url == null ){
            listFetchCallback.onList( new ArrayList<Joke>());
            return;
        }

        ChuckNorrisApp.getHttpBuster().makeGetRequest(url, map, new ApiCallback() {
            @Override
            public void done(Response response, JSONObject jsonObject, Exception e) {
                List<Joke> jokes = new ArrayList<Joke>();
                AppLogger.log(jsonObject.toString());

                if( jsonObject !=null && e ==null ){
                    JSONArray data = jsonObject.optJSONArray("value");
                    if ( data != null ){
                        for ( int i=0; i<data.length(); i++){
                            jokes.add( Joke.saveFromJson(data.optJSONObject(i)));
                        }
                    }
                }

                if ( listFetchCallback !=null ){
                    listFetchCallback.onList(jokes);
                }
            }
        });
    }

    @Override
    public long count() {
        return 0;
    }
}