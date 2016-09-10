package com.tikoyapps.justgothome.place;

import android.content.Context;
import com.tikoyapps.justgothome.BasePresenter;
import com.tikoyapps.justgothome.BaseView;
import com.tikoyapps.justgothome.data.Place;
import java.util.List;

/**
 * Created by xcptan on 10/09/2016.
 */
public interface PlaceContract {
    interface View extends BaseView<Presenter>{
        void updateList(List<Place> placeList);
        void showAddPlaceDialog();
    }

    interface Presenter extends BasePresenter{
        void requestPlaces();
        void addPlace();
        void deletePlace(String id);
    }
}
