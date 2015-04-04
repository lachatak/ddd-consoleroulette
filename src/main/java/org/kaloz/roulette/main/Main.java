package org.kaloz.roulette.main;

import org.kaloz.roulette.config.RootApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootApplicationConfig.class);
        applicationContext.registerShutdownHook();
    }
}
