package org.kaloz.roulette.domain.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.apache.commons.lang3.Validate;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final public class Pocket {

    private final int number;

    public static Pocket pocket(final int number) {
        Validate.isTrue(number >= 0 && number <= 36, "Winning number should be between 0 and 36!");
        return new Pocket(number);
    }

}
