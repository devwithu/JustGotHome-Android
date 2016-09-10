package com.tikoyapps.justgothome.place;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.tikoyapps.justgothome.data.Place;
import java.util.List;

/**
 * Created by xcptan on 10/09/2016.
 */
public class PlaceFragment extends Fragment implements PlaceContract.View {

    public static PlaceFragment newInstance(Bundle args) {
        PlaceFragment placeFragment = new PlaceFragment();
        placeFragment.setArguments(args);

        return placeFragment;
    }

    @Override
    public void updateList(List<Place> placeList) {

    }

    @Override
    public void showAddPlaceDialog() {

    }

    @Override
    public void setPresenter(PlaceContract.Presenter presenter) {

    }
}
