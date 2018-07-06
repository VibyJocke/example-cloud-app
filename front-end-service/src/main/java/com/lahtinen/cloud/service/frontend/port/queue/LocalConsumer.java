package com.lahtinen.cloud.service.frontend.port.queue;

import com.google.common.eventbus.EventBus;
import com.lahtinen.cloud.service.frontend.domain.PostId;
import com.lahtinen.cloud.service.frontend.port.queue.event.PostCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LocalConsumer implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalConsumer.class);

    private EventBus eventBus;

    public LocalConsumer(EventBus eventBus) {
        LOGGER.info("Using local event consumer");
        this.eventBus = eventBus;
    }

    @Override
    public void run() {
        var events = Arrays.asList(
                new PostCreatedEvent(PostId.create().toString(), "foo", "bar"),
                new PostCreatedEvent(PostId.create().toString(), "hello", "world")
        );
        events.forEach(event -> eventBus.post(event));

        LOGGER.info("Bootstrapped events on eventBus");
    }
}
