package org.kaloz.roulette.domain.exception;

import org.kaloz.roulette.domain.core.Player;

public class PlayerNotRegisteredException extends RuntimeException {

    private static final String MESSAGE = "%s is not registered for this game!";

    public PlayerNotRegisteredException(final Player player) {
        super(String.format(MESSAGE, player));
    }
}
