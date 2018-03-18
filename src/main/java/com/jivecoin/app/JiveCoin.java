package com.jivecoin.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class JiveCoin extends Application<JiveCoinConfiguration> {
    public static void main(String[] args) throws Exception {
        new JiveCoin().run(args);
    }

    @Override
    public String getName() {
        return "JiveCoin";
    }

    @Override
    public void initialize(Bootstrap<JiveCoinConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(JiveCoinConfiguration configuration,
                    Environment environment) {
        // nothing to do yet
    }

}
