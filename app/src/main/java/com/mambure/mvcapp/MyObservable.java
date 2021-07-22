package com.mambure.mvcapp;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MyObservable<OBSERVER> {

    private final Set<OBSERVER> listeners = new HashSet<>();

    public void registerListener(OBSERVER listener) {
        listeners.add(listener);
    }

    public void unregisterListener(OBSERVER listener) {
        listeners.remove(listener);
    }

    protected Set<OBSERVER> getListeners() {
        return Collections.unmodifiableSet(new HashSet<>(listeners));
    }
}
