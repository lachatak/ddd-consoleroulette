package org.kaloz.roulette.infrastucture.adapters.driving.console;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.DependsOn;

@Named
@Slf4j
@DependsOn("rouletteGameFileLoader")
public class ConsolePlayerBetReader {

    private final ConsolePlayerBetRunnable consolePlayerBetRunnable;
    private final ExecutorService executorService;

    @Inject
    public ConsolePlayerBetReader(final ExecutorService executorService, final ConsolePlayerBetRunnable consolePlayerBetRunnable) {
        this.executorService = executorService;
        this.consolePlayerBetRunnable = consolePlayerBetRunnable;
    }

    @PostConstruct
    public void readUserInput() {
        executorService.execute(consolePlayerBetRunnable);
    }

}
