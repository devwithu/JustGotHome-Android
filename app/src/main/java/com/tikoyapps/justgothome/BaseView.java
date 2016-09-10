package com.tikoyapps.justgothome;

import android.content.Context;

/**
 * Created by xcptan on 07/09/2016.
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
    Context getContext();
}
