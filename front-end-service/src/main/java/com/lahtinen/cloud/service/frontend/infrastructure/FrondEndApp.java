package com.lahtinen.cloud.service.frontend.infrastructure;

import com.google.common.eventbus.EventBus;
import com.lahtinen.cloud.service.frontend.application.PostApplication;
import com.lahtinen.cloud.service.frontend.application.PostProjection;
import com.lahtinen.cloud.service.frontend.port.queue.LocalConsumer;
import com.lahtinen.cloud.service.frontend.port.queue.LocalPublisher;
import com.lahtinen.cloud.service.frontend.port.queue.QueueConsumer;
import com.lahtinen.cloud.service.frontend.port.queue.QueuePublisher;
import com.lahtinen.cloud.service.frontend.port.rest.PostResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
    public void run(FrontEndAppConfiguration config, Environment environment) {
        var isLocal = config.isLocal();
        var commandPublisher = isLocal ? new LocalPublisher() : new QueuePublisher(config.getCommandQueueName());
        var postReadRepository = new PostProjection();
        var postResource = new PostResource(new PostApplication(commandPublisher, postReadRepository));
        environment.jersey().register(postResource);

        var eventBus = new EventBus();
        var eventConsumer = isLocal ? new LocalConsumer(eventBus) : new QueueConsumer(eventBus, config.getEventQueueName());

        eventBus.register(postReadRepository);
        environment.lifecycle().executorService("consumer").build().execute(eventConsumer);
    }
}