package org.kaloz.roulette.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.Validate;
import org.kaloz.roulette.domain.core.PlayerBet;
import org.kaloz.roulette.domain.core.Pocket;
import org.kaloz.roulette.domain.core.position.PlayerPosition;
import org.kaloz.roulette.domain.core.result.BetResult;
import org.kaloz.roulette.domain.exception.PlayerNotRegisteredException;

@Named
@Slf4j
public class Croupier {

    private final ResultBoard resultBoard;

    @Inject
    public Croupier(final ResultBoard resultBoard) {
        this.resultBoard = resultBoard;
    }

    public void registerPlayer(final RouletteGame rouletteGame, final PlayerPosition playerPosition) {
        Validate.notNull(rouletteGame, "RouletteGame cannot be null!");
        Validate.notNull(playerPosition, "PlayerPosition cannot be null!");

        log.info("Register player {}", playerPosition);

        rouletteGame.registerPlayer(playerPosition);
    }

    public void placeBet(final RouletteGame rouletteGame, final PlayerBet playerBet) {
        Validate.notNull(rouletteGame, "RouletteGame cannot be null!");
        Validate.notNull(playerBet, "PlayerBet cannot be null!");

        log.info("Place bet {}", playerBet);

        if (!rouletteGame.containsPlayers(playerBet.getPlayer())) {
            throw new PlayerNotRegisteredException(playerBet.getPlayer());
        }

        rouletteGame.placeBet(playerBet);
    }

    public void announceWinningPocket(final RouletteGame rouletteGame, final Pocket pocket) {
        Validate.notNull(rouletteGame, "RouletteGame cannot be null!");
        Validate.notNull(pocket, "Pocket cannot be null!");

        log.info("Winning number is {}", pocket);

        List<BetResult> betResults = rouletteGame.updatePlayerPositions(pocket);

        resultBoard.updateBetResults(pocket, betResults);
        resultBoard.updatePlayerPositions(rouletteGame.playerPositions());
    }
}
