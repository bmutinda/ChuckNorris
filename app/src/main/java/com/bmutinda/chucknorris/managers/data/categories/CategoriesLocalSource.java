package com.bmutinda.chucknorris.managers.data.categories;

import com.bmutinda.chucknorris.callbacks.ListFetchCallback;
import com.bmutinda.chucknorris.models.Category;

public class CategoriesLocalSource implements CategoriesDataSource {

    @Override
    public void getAll(ListFetchCallback<Category> callback) {
    }

    @Override
    public long count() {
        return 0;
    }
}