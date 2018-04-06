package com.jivecoin.app.bundle;

import com.jivecoin.app.JiveCoinConfiguration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FlywayBundle implements ConfiguredBundle<JiveCoinConfiguration> {

    private final Flyway flyway;

    public FlywayBundle(Flyway flyway) {
        this.flyway = flyway;
    }

    @Override
    public void run(JiveCoinConfiguration configuration, Environment environment) throws IOException {
        DataSourceFactory dsFactory = configuration.database;
        DataSource ds = dsFactory.build(environment.metrics(), "flyway");
        // point flyway to the database
        flyway.setDataSource(ds);

        // initialize the blockchain store
        String[] parts = dsFactory.getUrl().split(":");
        if (parts.length < 3) {
            throw new RuntimeException("Configuration for the database URL is missing hostname");
        }
        Path blockchainStore = Paths.get(parts[2]);
        if (Files.notExists(blockchainStore)) {
            Path dir = blockchainStore.getParent();
            Files.createDirectories(dir);
            // flyway needs to create baseline
            flyway.setBaselineOnMigrate(true);
        }

        // start the migration
        flyway.migrate();
    }

    @Override
    public void initialize(Bootstrap<?> bootstrap) {}

}
