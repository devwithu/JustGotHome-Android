package com.tikoyapps.justgothome;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.tikoyapps.justgothome.actions.Request;
import com.tikoyapps.justgothome.actions.Response;
import com.tikoyapps.justgothome.sms.AutoSmsService;

/**
 * Created by xcptan on 4/27/16.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Response.SEND_SMS_SUCCESS)) {
                Log.d(TAG, "SEND SMS SUCCESS");
            }
            else {
                if (intent.getAction().equals(Response.SEND_SMS_FAIL)) {
                    Log.d(TAG, "SEND SMS FAIL");
                }
            }
        }
    };

    private Button sendSmsButton;

    @Override
    protected void onCreate(
        @Nullable
        Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        sendSmsButton = (Button) findViewById(R.id.main_activity_sendsms_button);
        sendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalBroadcastManager.getInstance(MainActivity.this)
                    .sendBroadcast(new Intent(Request.SEND_SMS));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Response.SEND_SMS_FAIL);
        intentFilter.addAction(Response.SEND_SMS_SUCCESS);
        LocalBroadcastManager.getInstance(getApplicationContext())
            .registerReceiver(mReceiver, intentFilter);

        startService(new Intent(this, AutoSmsService.class));
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mReceiver);
        stopService(new Intent(this, AutoSmsService.class));
        super.onStop();
    }
}
