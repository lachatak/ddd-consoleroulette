package org.kaloz.roulette.domain.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import org.apache.commons.lang3.Validate;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
final public class Player {

    private final String name;

    public static Player player(final String name) {
        Validate.notBlank(name, "Player name cannot be empty!");
        return new Player(name);
    }
}
