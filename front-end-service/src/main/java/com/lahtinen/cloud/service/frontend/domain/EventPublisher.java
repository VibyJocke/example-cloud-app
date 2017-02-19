package com.lahtinen.cloud.service.frontend.domain;

import com.lahtinen.cloud.service.frontend.port.rest.queue.event.Event;

public interface EventPublisher {
    void publish(Event event);
}
