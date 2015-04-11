package org.kaloz.roulette.infrastucture.adapters.driving.console.assembler;

import static org.kaloz.roulette.domain.core.bet.Bet.bet;
import static org.kaloz.roulette.domain.core.Player.player;
import static org.kaloz.roulette.domain.core.PlayerBet.playerBet;

import javax.inject.Named;

import org.apache.commons.lang3.Validate;
import org.kaloz.roulette.domain.core.PlayerBet;

@Named
public class PlayerBetAssembler {

    private static final String FIELD_SEPARATOR = "\\s";

    public PlayerBet assemble(String playerInput) {
        String[] tokens = playerInput.split(FIELD_SEPARATOR);
        Validate.isTrue(tokens.length == 3, "3 space separated item must be provided!");
        return playerBet(player(tokens[0]), bet(tokens[1], tokens[2]));
    }

}
