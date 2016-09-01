package com.tikoyapps.justgothome.data;

import android.support.annotation.NonNull;
import java.util.List;

/**
 * Created by xcptan on 01/09/2016.
 */
public interface CellIdRepository {

    interface LoadCellIdsCallback {
        void onCellIdsLoaded(List<CellId> cellIds);
    }

    interface GetCellIdCallback {
        void onCellIdLoaded(CellId cellId);
    }

    void getCellIds(
        @NonNull
        LoadCellIdsCallback callback);

    void getCellId(
        @NonNull
        String id,
        @NonNull
        GetCellIdCallback callback);

    void saveCellId(
        @NonNull
        CellId cellId);

    void refreshData();
}
