package com.lahtinen.cloud.service.frontend.port.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lahtinen.cloud.service.frontend.domain.CommandPublisher;
import com.lahtinen.cloud.service.frontend.port.queue.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalPublisher implements CommandPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueConsumer.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public LocalPublisher() {
        LOGGER.info("Using local command publisher");
    }

    @Override
    public void publish(Command event) {
        try {
            LOGGER.info("Published message {}", MAPPER.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
