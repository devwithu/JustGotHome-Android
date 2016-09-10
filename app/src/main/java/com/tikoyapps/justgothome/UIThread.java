package com.tikoyapps.justgothome;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by xcptan on 10/09/2016.
 */
public class UIThread {

    private static UIThread sUIThread;
    private Handler mHandler;

    private UIThread() {
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public static Handler getInstance() {
        if (sUIThread == null) {
            synchronized (UIThread.class) {
                if (sUIThread == null) {
                    sUIThread = new UIThread();
                }
            }
        }
        return sUIThread.mHandler;
    }
}
