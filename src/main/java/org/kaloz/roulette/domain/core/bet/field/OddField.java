package org.kaloz.roulette.domain.core.bet.field;

import com.google.common.collect.Lists;

public class OddField extends Field {

    private OddField() {
        super(Lists.newArrayList(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35), FIELD_MULTIPLIER);
    }

    public static OddField oddField() {
        return new OddField();
    }

    @Override
    public String bet() {
        return "ODD";
    }
}
