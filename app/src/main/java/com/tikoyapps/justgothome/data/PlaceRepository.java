package com.tikoyapps.justgothome.data;

import android.support.annotation.NonNull;
import java.util.List;

/**
 * Created by xcptan on 01/09/2016.
 */
public interface PlaceRepository {

    interface LoadPlacesCallback {
        void onPlacesLoaded(List<Place> places);
    }

    interface GetPlaceCallback {
        void onPlaceLoaded(Place place);
    }

    void getPlaces(
        @NonNull
        LoadPlacesCallback callback);

    void getPlace(
        @NonNull
        String placeId,
        @NonNull
        GetPlaceCallback callback);

    void savePlace(
        @NonNull
        Place place);

    void refreshData();
}
