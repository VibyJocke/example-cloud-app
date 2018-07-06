package com.lahtinen.cloud.service.frontend.port.rest.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class CreatePostRequest {

    @NotEmpty
    private final String title;
    @NotEmpty
    private final String body;

    @JsonCreator
    public CreatePostRequest(@JsonProperty("title") String title,
                             @JsonProperty("body") String body) {
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
