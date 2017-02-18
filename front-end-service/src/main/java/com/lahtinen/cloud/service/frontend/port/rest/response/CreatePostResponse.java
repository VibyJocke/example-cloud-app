package com.lahtinen.cloud.service.frontend.port.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lahtinen.cloud.service.frontend.domain.PostId;

public class CreatePostResponse {
    @JsonProperty
    private final String id;

    public CreatePostResponse(PostId id) {
        this.id = id.toString();
    }
}
