package com.tikoyapps.justgothome.place;

import com.raizlabs.android.dbflow.runtime.FlowContentObserver;
import com.tikoyapps.justgothome.data.Place;
import com.tikoyapps.justgothome.data.PlaceRepository;
import java.util.List;

/**
 * Created by xcptan on 11/09/2016.
 */
public class PlacePresenter implements PlaceContract.Presenter {
    private PlaceRepository mPlaceRepository;
    private PlaceContract.View mView;

    private FlowContentObserver mObserver;

    public PlacePresenter(PlaceRepository placeRepository, PlaceContract.View view) {
        this.mPlaceRepository = placeRepository;
        this.mView = view;
        this.mObserver = new FlowContentObserver();
        this.mView.setPresenter(this);
    }

    @Override
    public void requestPlaces() {
        mPlaceRepository.getPlaces(new PlaceRepository.LoadPlacesCallback() {
            @Override
            public void onPlacesLoaded(List<Place> places) {
                mView.updateList(places);
            }
        });
    }

    @Override
    public void addPlace() {

    }

    @Override
    public void deletePlace(String id) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
