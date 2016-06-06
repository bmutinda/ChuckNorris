package com.bmutinda.chucknorris.managers.data.categories;

import com.bmutinda.chucknorris.callbacks.ListFetchCallback;
import com.bmutinda.chucknorris.models.Category;

public interface CategoriesDataSource {
    void getAll(ListFetchCallback<Category> callback);
    long count();
}