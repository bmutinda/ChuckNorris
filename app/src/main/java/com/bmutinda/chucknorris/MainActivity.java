package com.bmutinda.chucknorris;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bmutinda.chucknorris.callbacks.ListFetchCallback;
import com.bmutinda.chucknorris.models.Category;
import com.bmutinda.chucknorris.network.DataService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadCategories();
    }

    private void loadCategories(){

        DataService.getCategories(new ListFetchCallback<Category>() {
            @Override
            public void onList(List<Category> list) {
                Log.e("FADASD", list.toString() );
            }
        });
    }
}