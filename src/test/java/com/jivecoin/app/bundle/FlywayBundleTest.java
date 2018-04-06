package com.jivecoin.app.bundle;

import com.codahale.metrics.MetricRegistry;
import com.jivecoin.app.JiveCoinConfiguration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.setup.Environment;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlywayBundleTest {

    @Mock private Flyway flyway;
    @Mock private Environment environment;
    @Mock private MetricRegistry metricRegistry;
    @Mock private DataSourceFactory dsFactory;
    @Mock private ManagedDataSource dataSource;

    private FlywayBundle flywayBundle;
    private JiveCoinConfiguration configuration;

    @BeforeEach
    void setUp() {
        flywayBundle = new FlywayBundle(flyway);
        configuration = new JiveCoinConfiguration();
        configuration.database = dsFactory;
        String dbUrl = "jdbc:sqlite:."; // make the hostname '.' to avoid creating unnecessary files

        // define behavior
        when(environment.metrics()).thenReturn(metricRegistry);
        when(dsFactory.build(metricRegistry, "flyway")).thenReturn(dataSource);
        when(dsFactory.getUrl()).thenReturn(dbUrl);
    }

    @Test
    @DisplayName("invalid hostname throws exception")
    void testRun_HostnameNotDefined() {
        when(dsFactory.getUrl()).thenReturn("jdbc:sqlite");
        assertThrows(
            RuntimeException.class,
            () -> flywayBundle.run(configuration, environment),
            "Configuration for the database URL is missing hostname"
        );
    }

    @Test
    @DisplayName("should run flyway migrate")
    void testRun() throws IOException {
        // method under test
        flywayBundle.run(configuration, environment);

        // assertions
        verify(flyway, times(1)).setDataSource(dataSource);
        verify(flyway, times(1)).migrate();
        verifyNoMoreInteractions(flyway);
    }

}
