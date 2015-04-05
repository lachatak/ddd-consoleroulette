package org.kaloz.roulette.infrastucture.adapters.driving.loader.assembler;

import static org.kaloz.roulette.domain.core.Player.player;
import static org.kaloz.roulette.domain.core.position.PlayerPosition.playerPosition;

import java.math.BigDecimal;

import javax.inject.Named;

import org.apache.commons.lang3.Validate;
import org.kaloz.roulette.domain.core.position.PlayerPosition;

@Named
public class PlayerPositionAssembler {

    private static final String FIELD_SEPARATOR = ",";

    public PlayerPosition assemble(String line) {
        String[] tokens = line.split(FIELD_SEPARATOR);
        Validate.isTrue(tokens.length == 3, "3 comma separated line must be provided!");
        BigDecimal totalWin;
        BigDecimal totalBet;
        try {
            totalWin = new BigDecimal(tokens[1]);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Total win should be number!");
        }
        try {
            totalBet = new BigDecimal(tokens[2]);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Total bet should be number!");
        }
        return playerPosition(player(tokens[0]), totalWin, totalBet);
    }
}
