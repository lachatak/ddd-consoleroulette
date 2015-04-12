package org.kaloz.roulette.domain.exception;

public class PlayerNotRegisteredException extends RuntimeException {

    public PlayerNotRegisteredException(final String message) {
        super(message);
    }
}
