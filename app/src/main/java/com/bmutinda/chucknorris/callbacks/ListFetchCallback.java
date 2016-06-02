package com.bmutinda.chucknorris.callbacks;

import java.util.List;

public interface ListFetchCallback<T> {
    void onList(List<T> list);
}
