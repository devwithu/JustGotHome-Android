package com.tikoyapps.justgothome.data;

/**
 * Created by xcptan on 01/09/2016.
 */
public class Schedule {

    private int id;

    private int placeId;
    private int contactPersonId;

    public int getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(int contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
