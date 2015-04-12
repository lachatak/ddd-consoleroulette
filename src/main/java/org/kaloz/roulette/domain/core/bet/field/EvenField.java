package org.kaloz.roulette.domain.core.bet.field;

import java.math.BigDecimal;

import com.google.common.collect.Lists;

public class EvenField extends Field {

    private final static BigDecimal FIELD_MULTIPLIER = new BigDecimal(2);

    private EvenField() {
        super(Lists.newArrayList(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36), FIELD_MULTIPLIER);
    }

    public static EvenField evenField() {
        return new EvenField();
    }

    @Override
    public String bet() {
        return "EVEN";
    }
}
