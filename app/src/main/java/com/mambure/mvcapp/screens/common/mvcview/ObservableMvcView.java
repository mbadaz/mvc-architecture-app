package com.mambure.mvcapp.screens.common.mvcview;

public interface ObservableMvcView<OBSERVER> extends MvcView {

    void registerListener(OBSERVER observer);

    void unregisterListener(OBSERVER observer);
}
