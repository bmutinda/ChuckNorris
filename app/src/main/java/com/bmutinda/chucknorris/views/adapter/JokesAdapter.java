package com.bmutinda.chucknorris.views.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bmutinda.chucknorris.R;
import com.bmutinda.chucknorris.models.Joke;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokesViewHolder> {

    List<Joke> jokes;
    RecyclerView recyclerView;

    public JokesAdapter( RecyclerView recyclerView, List<Joke> jokes ){
        this.recyclerView = recyclerView;
        this.jokes = jokes;
    }

    @Override
    public JokesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JokesViewHolder(LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.layout_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(JokesViewHolder holder, int position) {
        Joke joke = jokes.get(position);
        if ( holder == null || joke == null ){
            return;
        }
        holder.jokeView.setText(joke.getJoke());
        holder.categoryView.setText(joke.getCategory());
    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    static class JokesViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.joke_view) public TextView jokeView;
        @Bind(R.id.category_view) public TextView categoryView;

        public JokesViewHolder(View parent){
            super(parent);
            ButterKnife.bind(this, parent);
        }
    }
}
