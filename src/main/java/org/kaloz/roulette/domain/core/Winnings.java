package org.kaloz.roulette.domain.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

import org.apache.commons.lang3.Validate;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final public class Winnings {

    private final BigDecimal amount;

    public static Winnings winnings(final BigDecimal amount) {
        Validate.notNull(amount, "Win cannot be null!");
        Validate.isTrue(amount.signum() >= 0, "Win cannot be negative number!");
        return new Winnings(amount);
    }

    public static Winnings zero() {
        return winnings(BigDecimal.ZERO);
    }
}
