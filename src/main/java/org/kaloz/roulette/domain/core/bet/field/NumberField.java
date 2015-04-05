package org.kaloz.roulette.domain.core.bet.field;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Lists;

public class NumberField extends Field {

    private NumberField(final int numberField) {
        super(Lists.newArrayList(numberField), NUMBER_MULTIPLIER);
    }

    public static NumberField numberField(final int numberField) {
        Validate.isTrue(numberField >= 0 && numberField <= 36, "Number field must be >= 0 and <= 36!");
        return new NumberField(numberField);
    }

    @Override
    public String bet() {
        return pockets.get(0).toString();
    }
}
