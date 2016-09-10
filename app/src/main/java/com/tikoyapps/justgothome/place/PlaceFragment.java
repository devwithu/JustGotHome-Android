package com.tikoyapps.justgothome.place;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tikoyapps.justgothome.R;
import com.tikoyapps.justgothome.data.Place;
import com.tikoyapps.justgothome.databinding.PlaceFragmentBinding;
import java.util.List;

/**
 * Created by xcptan on 10/09/2016.
 */
public class PlaceFragment extends Fragment implements PlaceContract.View {

    private PlaceContract.Presenter mPresenter;
    private PlaceFragmentBinding binding;

    public static PlaceFragment newInstance(Bundle args) {
        PlaceFragment placeFragment = new PlaceFragment();
        placeFragment.setArguments(args);

        return placeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
        @Nullable
        ViewGroup container,
        @Nullable
        Bundle savedInstanceState) {
        binding =
            PlaceFragmentBinding.bind(inflater.inflate(R.layout.place_fragment, container, false));
        return binding.getRoot();
    }

    @Override
    public void updateList(List<Place> placeList) {

    }

    @Override
    public void showAddPlaceDialog() {

    }

    @Override
    public void setPresenter(PlaceContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
