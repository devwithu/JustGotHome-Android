package com.tikoyapps.justgothome.cellid;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.tikoyapps.justgothome.R;
import com.tikoyapps.justgothome.data.CellIdRepositoryImpl;
import com.tikoyapps.justgothome.sms.AutoSmsService;

/**
 * Created by xcptan on 4/27/16.
 */
public class CellIdActivity extends AppCompatActivity {

    private static final String TAG = CellIdActivity.class.getSimpleName();

    @Override
    protected void onCreate(
        @Nullable
        Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        CellIdFragment cellIdFragment =
            (CellIdFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (null == cellIdFragment) {
            cellIdFragment = CellIdFragment.newInstance(null);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame, cellIdFragment);
            transaction.commit();

            //Create presenter, fragment will have reference of presenter through the setPresenter
            // invoked from BaseContract.View

            //Temporary, must use injection for repository
            new CellIdPresenter(this, new CellIdRepositoryImpl(),cellIdFragment);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!isMyServiceRunning(AutoSmsService.class)) {
            startService(new Intent(this, AutoSmsService.class));
        }
    }

    @Override
    protected void onStop() {
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
