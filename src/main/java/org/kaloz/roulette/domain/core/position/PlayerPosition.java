package org.kaloz.roulette.domain.core.position;

import static org.kaloz.roulette.domain.core.position.TotalBet.totalBet;
import static org.kaloz.roulette.domain.core.position.TotalWin.totalWin;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

import org.apache.commons.lang3.Validate;
import org.kaloz.roulette.domain.core.Player;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final public class PlayerPosition {

    private final Player player;
    private final TotalWin totalWin;
    private final TotalBet totalBet;

    public static PlayerPosition playerPosition(final Player player, final TotalWin totalWin, final TotalBet totalBet) {
        Validate.notNull(player, "Player cannot be null!");
        Validate.notNull(totalWin, "TotalWin cannot be null!");
        Validate.notNull(totalBet, "TotalBet cannot be null!");
        return new PlayerPosition(player, totalWin, totalBet);
    }

    public static PlayerPosition playerPosition(final Player player, final BigDecimal totalWin, final BigDecimal totalBet) {
        return playerPosition(player, totalWin(totalWin), totalBet(totalBet));
    }

    public static PlayerPosition playerPosition(final Player player) {
        return playerPosition(player, TotalWin.zero(), TotalBet.zero());
    }

    public PlayerPosition add(final BigDecimal totalWin, final BigDecimal totalBet) {
        return playerPosition(this.player, this.totalWin.add(totalWin), this.totalBet.add(totalBet));
    }
}
