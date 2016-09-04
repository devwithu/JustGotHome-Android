package com.tikoyapps.justgothome.sms;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import com.tikoyapps.justgothome.actions.Request;
import com.tikoyapps.justgothome.actions.Response;
import com.tikoyapps.justgothome.data.CellId;
import com.tikoyapps.justgothome.data.CellIdRepository;
import com.tikoyapps.justgothome.data.CellIdRepositoryImpl;
import java.util.Calendar;

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

            if (intent.getAction().equals(Request.GET_CID)) {
                CellLocation cellLocation = mTelephonyManager.getCellLocation();
                if (cellLocation instanceof GsmCellLocation) {
                    parseGsmLocation((GsmCellLocation) cellLocation);
                }
            }
        }
    };
    private PhoneStateListener mPhoneStateListener;
    private CellIdRepository mCellIdRepository;

    private void parseGsmLocation(GsmCellLocation gsmCellLocation) {

        int cid = gsmCellLocation.getCid();
        int lac = gsmCellLocation.getLac();
        int psc = gsmCellLocation.getPsc();

        Log.d(TAG, "cid: " + cid + " lac: " + lac + " psc: " + psc);

        CellId cellId = new CellId("" + cid);
        cellId.setTimestamp(Calendar.getInstance().getTimeInMillis());

        mCellIdRepository.saveCellId(cellId);
    }

    private TelephonyManager mTelephonyManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class ServiceBinder extends Binder {
        public AutoSmsService getService(){
            return AutoSmsService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Request.SEND_SMS);
        intentFilter.addAction(Request.GET_CID);
        LocalBroadcastManager.getInstance(getApplicationContext())
            .registerReceiver(mReceiver, intentFilter);
        mTelephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        mPhoneStateListener = new PhoneStateListener() {
            @Override
            public void onCellLocationChanged(CellLocation cellLocation) {
                if (cellLocation instanceof GsmCellLocation) {
                    parseGsmLocation((GsmCellLocation) cellLocation);
                    // Add to db with timestamp if place changed
                    // Run through relevant cell ids we'd want
                }
            }
        };
        mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CELL_LOCATION);
        mCellIdRepository = new CellIdRepositoryImpl();
    }

    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
