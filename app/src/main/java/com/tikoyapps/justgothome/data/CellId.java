package com.tikoyapps.justgothome.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import java.util.UUID;

/**
 * Created by xcptan on 01/09/2016.
 */
public class CellId extends RealmObject {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    private int cellId;

    private int placeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }
}
