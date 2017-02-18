package com.lahtinen.cloud.service.frontend.port.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lahtinen.cloud.service.frontend.domain.PostId;

public class PostResponse {

    @JsonProperty
    private final String id;

    @JsonProperty
    private final String title;

    @JsonProperty
    private final String body;

    public PostResponse(PostId id, String title, String body) {
        this.id = id.toString();
        this.title = title;
        this.body = body;
    }
}
