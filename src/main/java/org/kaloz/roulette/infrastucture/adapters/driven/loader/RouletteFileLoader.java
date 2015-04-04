package org.kaloz.roulette.infrastucture.adapters.driven.loader;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.kaloz.roulette.app.RouletteService;
import org.kaloz.roulette.infrastucture.adapters.driven.console.ConsolePlayerBetReader;
import org.kaloz.roulette.infrastucture.adapters.driven.loader.assembler.PlayerPositionAssembler;
import org.kaloz.roulette.infrastucture.adapters.driven.scheduled.WinningPocketAnnouncementScheduler;

@Named
@Slf4j
public class RouletteFileLoader {

    private final ConsolePlayerBetReader consolePlayerBetReader;
    private final WinningPocketAnnouncementScheduler winningPocketAnnouncementScheduler;
    private final RouletteService rouletteService;
    private final PlayerPositionAssembler playerPositionAssembler;
    private final InputStream playerFileInputStream;

    @Inject
    public RouletteFileLoader(final ConsolePlayerBetReader consolePlayerBetReader, final WinningPocketAnnouncementScheduler winningPocketAnnouncementScheduler,
            final RouletteService rouletteService, final PlayerPositionAssembler playerPositionAssembler, final InputStream playerFileInputStream) {
        this.consolePlayerBetReader = consolePlayerBetReader;
        this.winningPocketAnnouncementScheduler = winningPocketAnnouncementScheduler;
        this.rouletteService = rouletteService;
        this.playerPositionAssembler = playerPositionAssembler;
        this.playerFileInputStream = playerFileInputStream;
    }

    @PostConstruct
    public void loadConfigurationAndStartRoulette() {
        try {
            loadConfiguration();
            consolePlayerBetReader.readUserInput();
            winningPocketAnnouncementScheduler.scheduleAnnouncement();
        } catch (Exception e) {
            log.error("Cannot start application!", e);
        }
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
