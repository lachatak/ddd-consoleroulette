package org.kaloz.roulette.domain.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.apache.commons.lang3.Validate;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final public class RouletteGameId {

    private final String id;

    public static RouletteGameId rouletteGameId(final String id) {
        Validate.notBlank(id, "RouletteGameId cannot be empty!");
        return new RouletteGameId(id);
    }
}
