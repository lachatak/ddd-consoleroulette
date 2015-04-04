package org.kaloz.roulette.infrastucture.adapters.driven.console;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;

import org.kaloz.roulette.app.RouletteService;
import org.kaloz.roulette.infrastucture.adapters.driven.console.assembler.PlayerBetAssembler;

@Named
@Slf4j
public class ConsolePlayerBetRunnable implements Runnable {

    private final RouletteService rouletteService;
    private final PlayerBetAssembler playerBetAssembler;
    private final InputStream consoleInputStream;

    @Inject
    public ConsolePlayerBetRunnable(final RouletteService rouletteService, final PlayerBetAssembler playerBetAssembler, final InputStream consoleInputStream) {
        this.rouletteService = rouletteService;
        this.playerBetAssembler = playerBetAssembler;
        this.consoleInputStream = consoleInputStream;
    }

    public void run() {
        try (Scanner scanner = new Scanner(consoleInputStream)) {
            while (scanner.hasNextLine()) {
                try {
                    rouletteService.placeBet(playerBetAssembler.assemble(scanner.nextLine()));
                } catch (Exception e) {
                    log.error("Error processing user input!", e);
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
