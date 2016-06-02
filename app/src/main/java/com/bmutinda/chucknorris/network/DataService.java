package com.bmutinda.chucknorris.network;

import com.bmutinda.chucknorris.callbacks.ListFetchCallback;
import com.bmutinda.chucknorris.managers.ChuckNorrisApp;
import com.bmutinda.chucknorris.models.Category;
import com.bmutinda.httpbuster.ApiCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

abstract public class DataService {

    public static void getCategories(final ListFetchCallback<Category> callback){
        if ( callback == null){
            return;
        }

        ChuckNorrisApp.getHttpBuster().makeGetRequest("categories", null, new ApiCallback() {
            @Override
            public void done(JSONObject jsonObject, Exception e) {
                List<Category> categories = new ArrayList<>();
                if ( jsonObject !=null ){
                    JSONArray values = jsonObject.optJSONArray("value");
                    if ( values !=null){
                        for (int i=0; i<values.length(); i++){
                            categories.add( new Category(values.optString(i)));
                        }
                    }
                }
                callback.onList(categories);
            }
        });
    }
}
