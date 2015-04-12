package org.kaloz.roulette.domain.exception;

public class RouletteGameNotFoundException extends RuntimeException {

    public RouletteGameNotFoundException(final String message) {
        super(message);
    }
}
