package org.kaloz.roulette.config.adapters;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan("org.kaloz.roulette.infrastucture.adapters.driving")
public class DrivingInfrastructureLayerConfig {

    @Value("classpath:${player.definition.file}")
    private Resource playerFileResource;

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return Executors.newScheduledThreadPool(1);
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newSingleThreadExecutor();
    }

    @Bean
    public Random random() {
        return new SecureRandom();
    }

    @Bean
    public InputStream consoleInputStream() {
        return System.in;
    }

    @Bean
    public InputStream playerFileInputStream() throws IOException {
        return playerFileResource.getInputStream();
    }

}
