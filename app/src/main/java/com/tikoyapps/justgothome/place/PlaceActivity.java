package com.tikoyapps.justgothome.place;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.tikoyapps.justgothome.R;
import com.tikoyapps.justgothome.data.PlaceRepositoryImpl;

/**
 * Created by xcptan on 11/09/2016.
 */
public class PlaceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(
        @Nullable
        Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.place_activity);

        PlaceFragment placeFragment = (PlaceFragment) getSupportFragmentManager().findFragmentById(
            R.id.place_activity_content);
        if (null == placeFragment) {
            placeFragment = PlaceFragment.newInstance(null);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.place_activity_content, placeFragment);
            transaction.commit();
        }

        new PlacePresenter(new PlaceRepositoryImpl(), placeFragment);
    }
}
