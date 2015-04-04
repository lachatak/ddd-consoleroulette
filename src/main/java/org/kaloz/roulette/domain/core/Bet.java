package org.kaloz.roulette.domain.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

import org.apache.commons.lang3.Validate;
import org.kaloz.roulette.domain.core.field.Field;
import org.kaloz.roulette.domain.core.field.FieldFactory;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final public class Bet {

    private final Field field;
    private final BigDecimal amount;

    public static Bet bet(final String field, final String amount) {
        try {
            BigDecimal convertedAmount = new BigDecimal(amount);
            Validate.isTrue(convertedAmount.signum() > 0, "Bet amount should be greater then 0!");
            return new Bet(FieldFactory.createField(field), convertedAmount);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("Bet amount is not valid!");
        }
    }
}
