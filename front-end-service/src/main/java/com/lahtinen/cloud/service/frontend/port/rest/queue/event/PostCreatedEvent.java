package com.lahtinen.cloud.service.frontend.port.rest.queue.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lahtinen.cloud.service.frontend.domain.PostId;

public class PostCreatedEvent {

    private final PostId id;
    private final String title;
    private final String body;

    public PostCreatedEvent(@JsonProperty(value = "id", required = true) String id,
                            @JsonProperty(value = "title", required = true) String title,
                            @JsonProperty(value = "body", required = true) String body) {
        this.id = PostId.parse(id);
        this.title = title;
        this.body = body;
    }

    public PostId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}

