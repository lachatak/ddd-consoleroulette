package org.kaloz.roulette.domain.core.position;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

import org.apache.commons.lang3.Validate;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final public class TotalBet {

    private final BigDecimal amount;

    public static TotalBet totalBet(final BigDecimal totalBet) {
        Validate.notNull(totalBet, "TotalBet cannot be null!");
        Validate.isTrue(totalBet.signum() >= 0, "TotalBet cannot be negative!");
        return new TotalBet(totalBet);
    }

    public static TotalBet zero() {
        return totalBet(BigDecimal.ZERO);
    }

    public TotalBet add(final BigDecimal totalBet) {
        return totalBet(new BigDecimal(this.amount.add(totalBet).doubleValue()));
    }
}
