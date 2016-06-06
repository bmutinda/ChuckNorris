package com.bmutinda.chucknorris.network;

import com.bmutinda.chucknorris.callbacks.ListFetchCallback;
import com.bmutinda.chucknorris.managers.ChuckNorrisApp;
import com.bmutinda.chucknorris.managers.data.categories.CategoriesDataSource;
import com.bmutinda.chucknorris.models.Category;
import com.bmutinda.httpbuster.ApiCallback;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoriesRemoteSource implements CategoriesDataSource {

    @Override
    public void getAll(final ListFetchCallback<Category> listFetchCallback) {
        ChuckNorrisApp.getHttpBuster().makeGetRequest("categories", null, new ApiCallback() {
            @Override
            public void done(Response response, JSONObject jsonObject, Exception e) {
                List<Category> categories = new ArrayList<Category>();
                if( jsonObject !=null && e ==null ){
                    JSONArray data = jsonObject.optJSONArray("value");
                    if ( data != null ){
                        for ( int i=0; i<data.length(); i++){
                            Category category = new Category(data.optString(i));
                            category.save();
                            categories.add(category);
                        }
                    }
                }

                if ( listFetchCallback !=null ){
                    listFetchCallback.onList(categories);
                }
            }
        });
    }

    @Override
    public long count() {
        return 0;
    }
}