package com.lahtinen.cloud.service.frontend.domain;

import com.lahtinen.cloud.service.frontend.port.queue.command.Command;

public interface CommandPublisher {
    void publish(Command event);
}
