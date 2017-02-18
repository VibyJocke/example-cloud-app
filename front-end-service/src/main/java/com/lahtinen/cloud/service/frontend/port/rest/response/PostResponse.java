package com.lahtinen.cloud.service.frontend.port.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lahtinen.cloud.service.frontend.domain.Post;

public class PostResponse {

    @JsonProperty
    private final String id;

    @JsonProperty
    private final String title;

    @JsonProperty
    private final String body;

    public PostResponse(Post post) {
        this.id = post.getId().toString();
        this.title = post.getTitle();
        this.body = post.getBody();
    }
}
