package com.mambure.mvcapp;

public interface ObservableViewMvc<OBSERVER> extends ViewMvc {

    void registerListener(OBSERVER observer);

    void unregisterListener(OBSERVER observer);
}
