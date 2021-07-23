package com.mambure.mvcapp.screens.common;

import android.view.LayoutInflater;

import javax.inject.Inject;

public class MvcViewFactory {

    private final LayoutInflater mLayoutInflater;

    @Inject
    public MvcViewFactory(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }
}
