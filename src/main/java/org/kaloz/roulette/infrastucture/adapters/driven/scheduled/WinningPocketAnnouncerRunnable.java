package org.kaloz.roulette.infrastucture.adapters.driven.scheduled;

import static org.kaloz.roulette.domain.core.Pocket.pocket;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

import javax.inject.Inject;
import javax.inject.Named;

import org.kaloz.roulette.app.RouletteService;

@Named
@Slf4j
public class WinningPocketAnnouncerRunnable implements Runnable {

    private final RouletteService rouletteService;
    private final Random random;

    @Inject
    public WinningPocketAnnouncerRunnable(final RouletteService rouletteService, final Random random) {
        this.rouletteService = rouletteService;
        this.random = random;
    }

    public void run() {
        try {
            rouletteService.announceWinningPocket(pocket(random.nextInt(37)));
        } catch (Exception e) {
            log.error("error", e);
        }
    }
}
