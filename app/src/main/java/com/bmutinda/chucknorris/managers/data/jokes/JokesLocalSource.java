package com.bmutinda.chucknorris.managers.data.jokes;

import com.bmutinda.chucknorris.callbacks.ListFetchCallback;
import com.bmutinda.chucknorris.models.Category;
import com.bmutinda.chucknorris.models.Joke;

public class JokesLocalSource implements JokesDataSource {

    @Override
    public void getAll(ListFetchCallback<Joke> listFetchCallback) {
        listFetchCallback.onList(Joke.listAll(Joke.class));
    }

    @Override
    public void getAll(ListFetchCallback<Joke> listFetchCallback, Category category) {
        listFetchCallback.onList(
                Joke.find(Joke.class, "category LIKE '%"+category.getName()+"%'" )
        );
    }

    @Override
    public long count() {
        return Joke.count(Joke.class);
    }

    public long count( Category category ){
        return Joke.count(Joke.class, "category=?", new String[] {category.getName()} );
    }
}