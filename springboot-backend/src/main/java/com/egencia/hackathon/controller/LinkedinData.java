package com.egencia.hackathon.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
            {
                "comment": "...",
                "content": {
                    "title": "...",
                    "description": "...",
                    "submittedUrl": "...",
                    "submittedImageUrl": "..."
                },
                "visibility": {
                    "code": "anyone"
                }
            }
 */
public class LinkedinData {

    public String comment;
    public Content content = new Content();
    public Visibility visibility = new Visibility();

    public static class Content {
        public String title;
        public String description;
        @JsonProperty("submittedUrl") public String submittedUrl;
        @JsonProperty("submittedImageUrl") public String submittedImageUrl;
    }

    public static class Visibility {
        public String code = "anyone";
    }

}
