package com.lahtinen.cloud.service.frontend.port.queue.command;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePostCommand extends Command {

    @JsonProperty
    private final String title;
    @JsonProperty
    private final String body;

    public CreatePostCommand(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
