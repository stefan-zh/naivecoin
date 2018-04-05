package com.jivecoin.app;

import com.jivecoin.app.bundle.FlywayBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.flywaydb.core.Flyway;

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
        // add Flyway bundle
        Flyway flyway = new Flyway();
        bootstrap.addBundle(new FlywayBundle(flyway));
    }

    @Override
    public void run(JiveCoinConfiguration configuration,
                    Environment environment) {
        // nothing to do yet
    }

}
