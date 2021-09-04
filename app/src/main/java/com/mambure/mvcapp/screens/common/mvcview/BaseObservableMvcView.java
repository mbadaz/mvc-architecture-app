package com.mambure.mvcapp.screens.common.mvcview;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseObservableMvcView<OBSERVER> extends BaseMvcView {
    private final Set<OBSERVER> mObservers = new HashSet<>();

    public final void registerListener(OBSERVER observer) {
        mObservers.add(observer);
    }

    public final void unregisterListener(OBSERVER observer) {
        mObservers.remove(observer);
    }

    protected final Set<OBSERVER> getListeners() {
        return Collections.unmodifiableSet(mObservers);
    }
}
