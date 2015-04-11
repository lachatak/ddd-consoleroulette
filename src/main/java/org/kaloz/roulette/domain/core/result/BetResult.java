package org.kaloz.roulette.domain.core.result;

import static org.kaloz.roulette.domain.core.result.OutCome.LOSE;
import static org.kaloz.roulette.domain.core.result.OutCome.WIN;
import static org.kaloz.roulette.domain.core.result.Winnings.winnings;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

import org.apache.commons.lang3.Validate;
import org.kaloz.roulette.domain.core.PlayerBet;
import org.kaloz.roulette.domain.core.Pocket;
import org.kaloz.roulette.domain.core.bet.field.Field;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final public class BetResult {

    private final PlayerBet playerBet;
    private final OutCome outCome;
    private final Winnings winnings;

    public static BetResult betResult(final PlayerBet playerBet, final Pocket winningPocket) {
        Validate.notNull(playerBet, "PlayerBet cannot be null!");
        Validate.notNull(winningPocket, "WinningPocket cannot be null!");

        OutCome outCome = calculateOutCome(playerBet.getBet().getField(), winningPocket);
        Winnings winnings = calculateWinning(playerBet.getBet().getField(), winningPocket, playerBet.getBet().getAmount());
        return new BetResult(playerBet, outCome, winnings);
    }

    private static Winnings calculateWinning(final Field field, final Pocket winningPocket, final BigDecimal amount) {
        if (field.contains(winningPocket.getNumber())) {
            return winnings(new BigDecimal(amount.multiply(field.getMultiplier()).doubleValue()));
        } else {
            return Winnings.zero();
        }
    }

    private static OutCome calculateOutCome(final Field field, final Pocket winningPocket) {
        if (field.contains(winningPocket.getNumber())) {
            return WIN;
        } else {
            return LOSE;
        }
    }
}
