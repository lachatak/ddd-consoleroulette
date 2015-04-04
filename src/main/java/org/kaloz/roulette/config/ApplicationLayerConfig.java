package org.kaloz.roulette.config;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.kaloz.roulette.app")
public class ApplicationLayerConfig {

    @Bean
    public Lock lock() {
        return new ReentrantLock();
    }
}
