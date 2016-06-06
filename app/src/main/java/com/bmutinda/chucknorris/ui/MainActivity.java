package com.bmutinda.chucknorris.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bmutinda.chucknorris.R;
import com.bmutinda.chucknorris.callbacks.ListFetchCallback;
import com.bmutinda.chucknorris.managers.data.jokes.JokesDataService;
import com.bmutinda.chucknorris.models.Category;
import com.bmutinda.chucknorris.views.adapter.CategoriesPagerAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.data_viewpager) ViewPager viewPager;
    @Bind(R.id.tabs) TabLayout tabLayout;

    CategoriesPagerAdapter adapter;
    JokesDataService jokesDataService = JokesDataService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        loadCategories();
    }

    private void loadCategories(){
        jokesDataService.getCategories(new ListFetchCallback<Category>() {
            @Override
            public void onList(final List<Category> list) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        updateViewPager(list);
                    }
                });
            }
        });
    }

    private void updateViewPager(List<Category> categories){
        adapter = new CategoriesPagerAdapter(getSupportFragmentManager());
        for (Category category: categories){
            adapter.addFragment( CategoryFragment.newInstance(category), category.getName() );
        }
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}