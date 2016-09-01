package com.tikoyapps.justgothome;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.tikoyapps.justgothome.actions.Request;
import com.tikoyapps.justgothome.actions.Response;
import com.tikoyapps.justgothome.data.CellId;
import com.tikoyapps.justgothome.sms.AutoSmsService;
import java.util.ArrayList;

/**
 * Created by xcptan on 4/27/16.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;

    private CellIdListAdapter mCellIdListAdapter;

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

        mRecyclerView = (RecyclerView)findViewById(R.id.cellid_list);

        sendSmsButton = (Button) findViewById(R.id.main_activity_sendsms_button);
        sendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalBroadcastManager.getInstance(MainActivity.this)
                    .sendBroadcast(new Intent(Request.GET_CID));
            }
        });

        String[] requiredPermissions = new String[] {
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.SEND_SMS
        };

        ActivityCompat.requestPermissions(this, requiredPermissions, 200);

        mCellIdListAdapter = new CellIdListAdapter(this,new ArrayList<CellId>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mCellIdListAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
        @NonNull
        String[] permissions,
        @NonNull
        int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Toast.makeText(MainActivity.this, "Permissions granted", Toast.LENGTH_SHORT).show();
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
