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

    interface DeletePlaceCallback {
        void onPlaceDeleted(Place place);
    }

    interface AddPlaceCallback {
        void onPlaceAdded(Place place);
    }

    void getPlaces(
        @NonNull
        LoadPlacesCallback callback);

    void getPlace(
        @NonNull
        Long id,
        @NonNull
        GetPlaceCallback callback);

    void deletePlace(Long id, DeletePlaceCallback callback);

    void addPlace(
        @NonNull
        Place place, AddPlaceCallback callback);

    void refreshData();
}
