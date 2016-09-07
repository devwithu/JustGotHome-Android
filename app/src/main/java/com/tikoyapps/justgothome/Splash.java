package com.tikoyapps.justgothome;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.tikoyapps.justgothome.cellid.CellIdActivity;

/**
 * Created by xcptan on 04/09/2016.
 */
public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(
        @Nullable
        Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] requiredPermissions = new String[] {
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.SEND_SMS
        };

        ActivityCompat.requestPermissions(this, requiredPermissions, 200);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
        @NonNull
        String[] permissions,
        @NonNull
        int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean allGranted = true;
        for (int i : grantResults) {
            if (i == PackageManager.PERMISSION_DENIED) {
                allGranted = false;
            }
        }

        if (allGranted) {
            Toast.makeText(Splash.this, "Permissions granted", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, CellIdActivity.class));
            finish();
        }
        else {
            Toast.makeText(Splash.this,
                "Permissions denied. Please restart app and allow permissions.", Toast.LENGTH_SHORT)
                .show();
        }
    }
}
