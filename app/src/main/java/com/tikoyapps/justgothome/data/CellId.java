package com.tikoyapps.justgothome.data;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by xcptan on 01/09/2016.
 */

@Table(database = JGHDatabase.class)
public class CellId extends BaseModel{

    @PrimaryKey(autoincrement = true)
    Long id;

    @Column
    String cellId;

    @Column
    String placeId;

    @Column
    Long timestamp;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public CellId() {
    }

    public CellId(String cellId) {
        this.cellId = cellId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
