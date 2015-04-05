package org.kaloz.roulette.infrastucture.adapters.driving.loader;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.kaloz.roulette.app.RouletteService;
import org.kaloz.roulette.domain.RouletteGame;
import org.kaloz.roulette.domain.RouletteGameRepository;
import org.kaloz.roulette.domain.core.RouletteGameId;
import org.kaloz.roulette.infrastucture.adapters.driving.console.ConsolePlayerBetReader;
import org.kaloz.roulette.infrastucture.adapters.driving.loader.assembler.PlayerPositionAssembler;
import org.kaloz.roulette.infrastucture.adapters.driving.scheduled.WinningPocketAnnouncementScheduler;

@Named
@Slf4j
public class RouletteGameFileLoader {

    private final ConsolePlayerBetReader consolePlayerBetReader;
    private final WinningPocketAnnouncementScheduler winningPocketAnnouncementScheduler;
    private final RouletteService rouletteService;
    private final RouletteGameRepository rouletteGameRepository;
    private final PlayerPositionAssembler playerPositionAssembler;
    private final InputStream playerFileInputStream;

    @Inject
    public RouletteGameFileLoader(final ConsolePlayerBetReader consolePlayerBetReader, final WinningPocketAnnouncementScheduler winningPocketAnnouncementScheduler,
            final RouletteService rouletteService, final RouletteGameRepository rouletteGameRepository, final PlayerPositionAssembler playerPositionAssembler,
            final InputStream playerFileInputStream) {
        this.consolePlayerBetReader = consolePlayerBetReader;
        this.winningPocketAnnouncementScheduler = winningPocketAnnouncementScheduler;
        this.rouletteService = rouletteService;
        this.rouletteGameRepository = rouletteGameRepository;
        this.playerPositionAssembler = playerPositionAssembler;
        this.playerFileInputStream = playerFileInputStream;
    }

    @PostConstruct
    public void loadConfigurationAndStartRoulette() {
        try {
            initialiseStore();
            loadConfiguration();
            startRouletteGame();
        } catch (Exception e) {
            log.error("Cannot start application!", e);
        }
    }

    private RouletteGameId initialiseStore() {
        RouletteGameId rouletteGameId = rouletteGameRepository.store(new RouletteGame());
        log.info("Game with {} is initialised!");
        return rouletteGameId;
    }

    private void startRouletteGame() {
        consolePlayerBetReader.readUserInput();
        winningPocketAnnouncementScheduler.scheduleAnnouncement();
    }

    private void loadConfiguration() throws IOException {
        try (Scanner scanner = new Scanner(playerFileInputStream)) {
            while (scanner.hasNextLine()) {
                try {
                    rouletteService.registerPlayer(playerPositionAssembler.assemble(scanner.nextLine()));
                } catch (Exception e) {
                    log.error("Error processing file input!", e);
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
