package com.lahtinen.cloud.service.frontend.port.queue.command;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeletePostCommand extends Command {

    @JsonProperty
    private final String id;

    public DeletePostCommand(String id) {
        this.id = id;
    }
}
