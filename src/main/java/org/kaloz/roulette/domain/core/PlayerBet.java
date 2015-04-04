package org.kaloz.roulette.domain.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.apache.commons.lang3.Validate;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final public class PlayerBet {

    private final Player player;
    private final Bet bet;

    public static PlayerBet playerBet(final Player player, final Bet bet) {
        Validate.notNull(player, "Player cannot be null!");
        Validate.notNull(bet, "Bet cannot be null!");
        return new PlayerBet(player, bet);
    }

}
