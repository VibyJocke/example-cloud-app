package com.lahtinen.cloud.service.frontend.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

public class FrontEndAppConfiguration extends Configuration {

    @JsonProperty
    private String commandQueueName;

    @JsonProperty
    private String eventQueueName;

    public String getCommandQueueName() {
        return commandQueueName;
    }

    public String getEventQueueName() {
        return eventQueueName;
    }
}
