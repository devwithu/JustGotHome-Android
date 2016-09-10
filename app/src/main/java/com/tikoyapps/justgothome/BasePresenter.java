package com.tikoyapps.justgothome;

/**
 * Created by xcptan on 07/09/2016.
 */
public interface BasePresenter {

    // Do initialization here like rx subscription
    void start();

    // Do destructors here like rx unsubscribe or DBFlow unwatch tables
    void stop();

}
