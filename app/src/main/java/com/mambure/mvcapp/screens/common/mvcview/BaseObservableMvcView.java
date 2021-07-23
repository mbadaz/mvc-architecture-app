package com.mambure.mvcapp.screens.common.mvcview;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseObservableMvcView<OBSERVER> extends BaseMvcView
        implements ObservableMvcView<OBSERVER> {

    private final Set<OBSERVER> mObservers = new HashSet<>();

    @Override
    public final void registerListener(OBSERVER observer) {
        mObservers.add(observer);
    }

    @Override
    public final void unregisterListener(OBSERVER observer) {
        mObservers.remove(observer);
    }

    protected final Set<OBSERVER> getListeners() {
        return Collections.unmodifiableSet(mObservers);
    }
}
