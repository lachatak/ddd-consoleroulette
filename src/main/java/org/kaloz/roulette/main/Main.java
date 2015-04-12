package org.kaloz.roulette.main;

import org.kaloz.roulette.config.RootApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(RootApplicationConfig.class, args);
    }
}
