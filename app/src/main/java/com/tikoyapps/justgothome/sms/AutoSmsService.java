package com.tikoyapps.justgothome.sms;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.tikoyapps.justgothome.actions.Request;
import com.tikoyapps.justgothome.actions.Response;

/**
 * Created by xcptan on 4/27/16.
 */
public class AutoSmsService extends Service {

    private static final String TAG = AutoSmsService.class.getSimpleName();

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Request.SEND_SMS)) {
                Log.d(TAG, "Trying to send SMS");
                LocalBroadcastManager.getInstance(AutoSmsService.this)
                    .sendBroadcast(new Intent(Response.SEND_SMS_SUCCESS));
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Request.SEND_SMS);
        LocalBroadcastManager.getInstance(getApplicationContext())
            .registerReceiver(mReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
