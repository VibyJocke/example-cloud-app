package com.lahtinen.cloud.service.frontend.infrastructure;

import com.google.common.eventbus.EventBus;
import com.lahtinen.cloud.service.frontend.application.PostApplication;
import com.lahtinen.cloud.service.frontend.application.PostProjection;
import com.lahtinen.cloud.service.frontend.domain.EventPublisher;
import com.lahtinen.cloud.service.frontend.domain.PostReadRepository;
import com.lahtinen.cloud.service.frontend.port.rest.PostResource;
import com.lahtinen.cloud.service.frontend.port.rest.queue.QueueConsumer;
import com.lahtinen.cloud.service.frontend.port.rest.queue.QueuePublisher;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.concurrent.Executors;

public class FrondEndApp extends Application<FrontEndAppConfiguration> {

    public static void main(String[] args) throws Exception {
        new FrondEndApp().run(args);
    }

    @Override
    public String getName() {
        return "front-end-app";
    }

    @Override
    public void initialize(Bootstrap<FrontEndAppConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(FrontEndAppConfiguration configuration, Environment environment) {
        final EventPublisher eventPublisher = new QueuePublisher(configuration.getCommandQueueName());
        final PostReadRepository postReadRepository = new PostProjection();
        final PostResource postResource = new PostResource(new PostApplication(eventPublisher, postReadRepository));
        environment.jersey().register(postResource);

        final EventBus eventBus = new EventBus();
        final QueueConsumer eventConsumer = new QueueConsumer(eventBus, configuration.getEventQueueName());

        eventBus.register(postReadRepository);
        Executors.newSingleThreadExecutor().execute(eventConsumer);
    }
}