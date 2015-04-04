package org.kaloz.roulette.infrastucture.adapters.driven.console;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Slf4j
public class ConsolePlayerBetReader {

    private final ConsolePlayerBetRunnable consolePlayerBetRunnable;
    private final ExecutorService executorService;

    @Inject
    public ConsolePlayerBetReader(final ExecutorService executorService, final ConsolePlayerBetRunnable consolePlayerBetRunnable) {
        this.executorService = executorService;
        this.consolePlayerBetRunnable = consolePlayerBetRunnable;
    }

    public void readUserInput() {
        executorService.execute(consolePlayerBetRunnable);
    }

}
