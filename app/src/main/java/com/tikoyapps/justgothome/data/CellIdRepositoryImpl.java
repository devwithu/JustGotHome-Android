package com.tikoyapps.justgothome.data;

import android.support.annotation.NonNull;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xcptan on 01/09/2016.
 */
public class CellIdRepositoryImpl implements CellIdRepository {

    @Override
    public void getCellIds(
        @NonNull
        LoadCellIdsCallback callback) {

        List<CellId> cellIdList = new ArrayList<CellId>();
        cellIdList.addAll(SQLite.select().from(CellId.class).queryList());

        callback.onCellIdsLoaded(cellIdList);
    }

    @Override
    public void getCellId(
        @NonNull
        String id,
        @NonNull
        GetCellIdCallback callback) {

        CellId cell = SQLite.select()
            .from(CellId.class)
            .where(CellId_Table.id.eq(Long.valueOf(id)))
            .querySingle();
        callback.onCellIdLoaded(cell);
    }

    @Override
    public void saveCellId(
        @NonNull
        CellId cellId) {
        cellId.save();
    }

    @Override
    public void refreshData() {

    }
}
