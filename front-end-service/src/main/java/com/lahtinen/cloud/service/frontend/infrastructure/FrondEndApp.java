package com.lahtinen.cloud.service.frontend.infrastructure;

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
    public void run(FrontEndAppConfiguration configuration, Environment environment) {
        // nothing to do yet
    }
}