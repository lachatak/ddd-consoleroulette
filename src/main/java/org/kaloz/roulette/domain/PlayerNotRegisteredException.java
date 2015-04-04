package org.kaloz.roulette.domain;

public class PlayerNotRegisteredException extends RuntimeException {

    public PlayerNotRegisteredException(final String message) {
        super(message);
    }
}
