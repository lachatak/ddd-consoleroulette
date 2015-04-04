package org.kaloz.roulette.domain.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

import org.apache.commons.lang3.Validate;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final public class TotalWin {

    private final BigDecimal amount;

    public static TotalWin totalWin(final BigDecimal totalWin) {
        Validate.notNull(totalWin, "TotalWin cannot be null!");
        Validate.isTrue(totalWin.signum() >= 0, "TotalWin cannot be negative!");
        return new TotalWin(totalWin);
    }

    public static TotalWin zero() {
        return totalWin(BigDecimal.ZERO);
    }

    public TotalWin add(final BigDecimal totalWin) {
        return totalWin(this.amount.add(totalWin));
    }

}
