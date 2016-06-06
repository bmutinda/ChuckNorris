package com.bmutinda.chucknorris.managers.data.jokes;

import com.bmutinda.chucknorris.callbacks.ListFetchCallback;
import com.bmutinda.chucknorris.managers.data.categories.CategoriesLocalSource;
import com.bmutinda.chucknorris.models.Category;
import com.bmutinda.chucknorris.models.Joke;
import com.bmutinda.chucknorris.network.CategoriesRemoteSource;
import com.bmutinda.chucknorris.network.JokesRemoteSource;

public class JokesDataService {

    static JokesDataService instance = new JokesDataService();
    JokesLocalSource jokesLocalSource;
    JokesRemoteSource jokesRemoteSource;
    CategoriesLocalSource categoriesLocalSource;
    CategoriesRemoteSource categoriesRemoteSource;

    private JokesDataService(){
        this.jokesLocalSource = new JokesLocalSource();
        this.jokesRemoteSource = new JokesRemoteSource();
        this.categoriesLocalSource = new CategoriesLocalSource();
        this.categoriesRemoteSource = new CategoriesRemoteSource();
    }

    private synchronized JokesRemoteSource getJokesRemoteSource(){
        return jokesRemoteSource;
    }

    private synchronized JokesLocalSource getJokesLocalSource(){
        return jokesLocalSource;
    }

    private synchronized CategoriesRemoteSource getCategoriesRemoteSource(){
        return categoriesRemoteSource;
    }

    private synchronized CategoriesLocalSource getCategoriesLocalSource(){
        return categoriesLocalSource;
    }

    public void getJokes(ListFetchCallback<Joke> callback){
        if ( getJokesLocalSource().count()>0 ){
            getJokesLocalSource().getAll(callback);
            return;
        }

        getJokesRemoteSource().getAll(callback);
    }

    public void getJokes(ListFetchCallback<Joke> callback, Category category){
        if ( getJokesLocalSource().count(category)>0 ){
            getJokesLocalSource().getAll(callback, category);
            return;
        }

        getJokesRemoteSource().getAll(callback, category);
    }

    public void getCategories(ListFetchCallback<Category> callback ){
        if (getCategoriesLocalSource().count()>0 ){
            getCategoriesLocalSource().getAll(callback);
            return;
        }

        getCategoriesRemoteSource().getAll(callback);
    }

    public static JokesDataService getInstance(){
        return instance;
    }
}
