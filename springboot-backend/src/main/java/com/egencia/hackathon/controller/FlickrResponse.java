package com.egencia.hackathon.controller;

import java.util.List;

public class FlickrResponse {

    public Photos photos;

    public static class Photos {
        public List<Photo> photo;
    }

    public static class Photo {
        public String url_z;
        public String height_z;
        public String width_z;
    }

}
