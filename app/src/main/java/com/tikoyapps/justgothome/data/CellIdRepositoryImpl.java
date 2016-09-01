package com.tikoyapps.justgothome.data;

import android.support.annotation.NonNull;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xcptan on 01/09/2016.
 */
public class CellIdRepositoryImpl implements CellIdRepository {

    @Override
    public void getCellIds(
        @NonNull
        LoadCellIdsCallback callback) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<CellId> realmResults = realm.where(CellId.class).findAll();
        List<CellId> cellIdList =
            Arrays.asList(realmResults.toArray(new CellId[realmResults.size()]));
        callback.onCellIdsLoaded(cellIdList);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void getCellId(
        @NonNull
        String placeId,
        @NonNull
        GetCellIdCallback callback) {

    }

    @Override
    public void saveCellId(
        @NonNull
        CellId cellId) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        CellId realmCellId = realm.createObject(CellId.class);
        realmCellId.setCellId(cellId.getCellId());
        realm.copyToRealm(realmCellId);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void refreshData() {

    }
}
