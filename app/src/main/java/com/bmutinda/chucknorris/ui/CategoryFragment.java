package com.bmutinda.chucknorris.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bmutinda.chucknorris.R;
import com.bmutinda.chucknorris.callbacks.ListFetchCallback;
import com.bmutinda.chucknorris.managers.data.jokes.JokesDataService;
import com.bmutinda.chucknorris.models.Category;
import com.bmutinda.chucknorris.models.Joke;
import com.bmutinda.chucknorris.views.adapter.JokesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryFragment extends Fragment implements ListFetchCallback<Joke> {
    Category category;
    @Bind(R.id.jokes_recycler_view) RecyclerView recyclerView;
    List<Joke> jokes = new ArrayList<>();
    JokesAdapter adapter;

    public CategoryFragment(){
        super();
    }

    public static CategoryFragment newInstance(Category category){
        CategoryFragment fragment = new CategoryFragment();
        fragment.category = category;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        adapter = new JokesAdapter(recyclerView, jokes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        loadJokes();
    }

    void loadJokes(){
        JokesDataService.getInstance().getJokes( this, category);
    }

    @Override
    public void onList(final List<Joke> list) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                jokes.clear();
                jokes.addAll(list);
                adapter.notifyItemRangeInserted(0, list.size()-1 );
            }
        });
    }
}