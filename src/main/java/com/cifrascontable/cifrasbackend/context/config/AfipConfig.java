package com.cifrascontable.cifrasbackend.context.config;

import com.cifrascontable.cifrasbackend.integration.afip.AfipClient;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is responsible for configuring the Afip Http configuration.
 */
@Configuration
public class AfipConfig {

    @Value("${afip.timeout.connect}")
    private int timeoutConnect;
    @Value("${afip.timeout.read}")
    private int timeoutRead;
    @Value("${afip.rest.host}")
    private String host;

    @Bean
    public AfipClient getAfipClient() {
        return new AfipClient(
            this.getHttpClient(),
            "",
            "",
            this.host,
            ""
        );
    }

    private OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder().connectTimeout(this.timeoutConnect, TimeUnit.MILLISECONDS)
            .readTimeout(this.timeoutRead, TimeUnit.MILLISECONDS)
            .build();
    }
}