package com.metrics.healthchecks;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.metriks.healthchecks.DatabaseHealthCheck;
import com.metriks.healthchecks.UserCenterHealthCheck;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class HealthCheckIntegrationTest {
    
    @Test
    public void whenUseHealthCheck_thenHealthChecked() {
        HealthCheckRegistry healthCheckRegistry = new HealthCheckRegistry();

        healthCheckRegistry.register("db", new DatabaseHealthCheck());
        healthCheckRegistry.register("uc", new UserCenterHealthCheck());

        assertThat(healthCheckRegistry.getNames().size(), equalTo(2));

        Map<String, HealthCheck.Result> results = healthCheckRegistry.runHealthChecks();

        assertFalse(results.isEmpty());

        results.forEach((k, v) -> assertTrue(v.isHealthy()));

        healthCheckRegistry.unregister("uc");

        assertThat(healthCheckRegistry.getNames().size(), equalTo(1));
    }
}