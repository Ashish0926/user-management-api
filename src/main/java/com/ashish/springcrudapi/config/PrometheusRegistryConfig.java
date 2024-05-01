package com.ashish.springcrudapi.config;

import static com.ashish.springcrudapi.constant.PrometheusCounter.CREATE_USER_TOTAL;
import static com.ashish.springcrudapi.constant.PrometheusCounter.DELETE_USER_BY_ID_TOTAL;
import static com.ashish.springcrudapi.constant.PrometheusCounter.GET_USERS_TOTAL;
import static com.ashish.springcrudapi.constant.PrometheusCounter.GET_USER_BY_ID_TOTAL;
import static com.ashish.springcrudapi.constant.PrometheusCounter.TOTAL_REQUESTS_TO_DELETE_USER_BY_ID_DESCRIPTION;
import static com.ashish.springcrudapi.constant.PrometheusCounter.TOTAL_REQUESTS_TO_GET_USERS_DESCRIPTION;
import static com.ashish.springcrudapi.constant.PrometheusCounter.TOTAL_REQUESTS_TO_GET_USER_BY_ID_DESCRIPTION;
import static com.ashish.springcrudapi.constant.PrometheusCounter.TOTAL_REQUESTS_TO_POST_USER_DESCRIPTION;
import static com.ashish.springcrudapi.constant.PrometheusCounter.TOTAL_REQUESTS_TO_UPDATE_USER_BY_ID_DESCRIPTION;
import static com.ashish.springcrudapi.constant.PrometheusCounter.UPDATE_USER_BY_ID_TOTAL;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrometheusRegistryConfig {

    private final MeterRegistry meterRegistry;

    public PrometheusRegistryConfig(final MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void init() {
        buildCounter(CREATE_USER_TOTAL, TOTAL_REQUESTS_TO_POST_USER_DESCRIPTION);
        buildCounter(GET_USER_BY_ID_TOTAL, TOTAL_REQUESTS_TO_GET_USER_BY_ID_DESCRIPTION);
        buildCounter(GET_USERS_TOTAL, TOTAL_REQUESTS_TO_GET_USERS_DESCRIPTION);
        buildCounter(UPDATE_USER_BY_ID_TOTAL, TOTAL_REQUESTS_TO_UPDATE_USER_BY_ID_DESCRIPTION);
        buildCounter(DELETE_USER_BY_ID_TOTAL, TOTAL_REQUESTS_TO_DELETE_USER_BY_ID_DESCRIPTION);
    }

    private void buildCounter(final String name, final String description) {
        Counter.builder(name)
            .description(description)
            .register(meterRegistry);
    }
}
