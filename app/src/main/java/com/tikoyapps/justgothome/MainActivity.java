package com.tikoyapps.justgothome;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.raizlabs.android.dbflow.runtime.FlowContentObserver;
import com.raizlabs.android.dbflow.sql.language.SQLCondition;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.Model;
import com.tikoyapps.justgothome.actions.Request;
import com.tikoyapps.justgothome.actions.Response;
import com.tikoyapps.justgothome.data.CellId;
import com.tikoyapps.justgothome.data.CellIdRepository;
import com.tikoyapps.justgothome.data.CellIdRepositoryImpl;
import com.tikoyapps.justgothome.sms.AutoSmsService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xcptan on 4/27/16.
 */
public class MainActivity extends AppCompatActivity {

    FlowContentObserver mFlowContentObserver = new FlowContentObserver();

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

    private FloatingActionButton sendSmsButton;

    private CellIdRepository mCellIdRepository;

    @Override
    protected void onCreate(
        @Nullable
        Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        mCellIdRepository = new CellIdRepositoryImpl();

        mRecyclerView = (RecyclerView) findViewById(R.id.cellid_list);

        sendSmsButton = (FloatingActionButton) findViewById(R.id.main_activity_getcellid_button);
        sendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalBroadcastManager.getInstance(MainActivity.this)
                    .sendBroadcast(new Intent(Request.GET_CID));
            }
        });

        mCellIdListAdapter = new CellIdListAdapter(this, new ArrayList<CellId>());
        mRecyclerView.setLayoutManager(
            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mCellIdListAdapter);

        mCellIdRepository.getCellIds(new CellIdRepository.LoadCellIdsCallback() {
            @Override
            public void onCellIdsLoaded(List<CellId> cellIds) {
                mCellIdListAdapter.updateList(cellIds);
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

        mFlowContentObserver.registerForContentChanges(this, CellId.class);
        mFlowContentObserver.addModelChangeListener(
            new FlowContentObserver.OnModelStateChangedListener() {
                @Override
                public void onModelStateChanged(
                    @Nullable
                    Class<? extends Model> table, BaseModel.Action action,
                    @NonNull
                    SQLCondition[] primaryKeyValues) {
                    mCellIdRepository.getCellIds(new CellIdRepository.LoadCellIdsCallback() {
                        @Override
                        public void onCellIdsLoaded(List<CellId> cellIds) {
                            mCellIdListAdapter.updateList(cellIds);
                        }
                    });
                }
            });

        if (!isMyServiceRunning(AutoSmsService.class)) {
            startService(new Intent(this, AutoSmsService.class));
        }
    }

    @Override
    protected void onStop() {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mReceiver);
        mFlowContentObserver.unregisterForContentChanges(this);
        super.onStop();
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(
            Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
