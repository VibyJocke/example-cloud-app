package com.lahtinen.cloud.service.frontend.port.rest.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePostRequest {

    private final String title;
    private final String body;

    @JsonCreator
    public CreatePostRequest(@JsonProperty(value = "title", required = true) String title,
                             @JsonProperty(value = "body", required = true) String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
