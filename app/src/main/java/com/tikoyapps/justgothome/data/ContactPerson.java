package com.tikoyapps.justgothome.data;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by xcptan on 01/09/2016.
 */
public class ContactPerson extends RealmObject {

    @PrimaryKey
    private int id;

    private String displayName;
    private String nickName;
    private String contactNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}

