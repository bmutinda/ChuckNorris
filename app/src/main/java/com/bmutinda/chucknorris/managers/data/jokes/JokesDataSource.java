package com.bmutinda.chucknorris.managers.data.jokes;

import com.bmutinda.chucknorris.callbacks.ListFetchCallback;
import com.bmutinda.chucknorris.models.Category;
import com.bmutinda.chucknorris.models.Joke;

public interface JokesDataSource {
    void getAll(ListFetchCallback<Joke> listFetchCallback);
    void getAll(ListFetchCallback<Joke> listFetchCallback, Category category );
    long count();
}