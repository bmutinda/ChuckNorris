package com.bmutinda.chucknorris.network;

import com.bmutinda.chucknorris.callbacks.ListFetchCallback;
import com.bmutinda.chucknorris.managers.ChuckNorrisApp;
import com.bmutinda.chucknorris.managers.data.jokes.JokesDataSource;
import com.bmutinda.chucknorris.models.Category;
import com.bmutinda.chucknorris.models.Joke;
import com.bmutinda.httpbuster.ApiCallback;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JokesRemoteSource implements JokesDataSource {

    @Override
    public void getAll(final ListFetchCallback<Joke> listFetchCallback) {
        getAll("jokes", listFetchCallback);
    }

    @Override
    public void getAll(ListFetchCallback<Joke> listFetchCallback, Category category) {
        getAll(String.format("jokes?limitTo=[%s]", category.getName()), listFetchCallback);
    }

    private void getAll( String url, final ListFetchCallback<Joke> listFetchCallback){
        if ( url == null ){
            listFetchCallback.onList( new ArrayList<Joke>());
            return;
        }

        ChuckNorrisApp.getHttpBuster().makeGetRequest(url, null, new ApiCallback() {
            @Override
            public void done(Response response, JSONObject jsonObject, Exception e) {
                List<Joke> jokes = new ArrayList<Joke>();
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