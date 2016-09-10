package com.tikoyapps.justgothome.data;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xcptan on 01/09/2016.
 */
public class PlaceRepositoryImpl implements PlaceRepository {
    @Override
    public void getPlaces(
        @NonNull
        LoadPlacesCallback callback) {

        List<Place> placeList = new ArrayList<Place>();
        placeList.addAll(SQLite.select().from(Place.class).queryList());

        callback.onPlacesLoaded(placeList);
    }

    @Override
    public void getPlace(
        @NonNull
        Long id,
        @NonNull
        GetPlaceCallback callback) {

        Place cell = SQLite.select().from(Place.class).where(Place_Table.id.eq(id)).querySingle();
        callback.onPlaceLoaded(cell);
    }

    @Override
    public void deletePlace(Long id, DeletePlaceCallback callback) {
        Place place = SQLite.delete().from(Place.class).where(Place_Table.id.eq(id)).querySingle();
        callback.onPlaceDeleted(place);
    }

    @Override
    public void addPlace(
        @NonNull
        Place place, AddPlaceCallback callback) {
        place.save();
        callback.onPlaceAdded(place);
    }

    @Override
    public void refreshData() {

    }
}
