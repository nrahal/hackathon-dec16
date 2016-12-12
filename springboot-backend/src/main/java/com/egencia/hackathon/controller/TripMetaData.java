package com.egencia.hackathon.controller;

/**
 * Created by rcrepin on 12/12/16.
 */
public class TripMetaData {

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TripMetaData{" +
                "id='" + id + '\'' +
                '}';
    }
}
