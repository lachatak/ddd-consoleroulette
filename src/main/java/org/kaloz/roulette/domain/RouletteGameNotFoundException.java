package org.kaloz.roulette.domain;

public class RouletteGameNotFoundException extends RuntimeException {

    public RouletteGameNotFoundException(final String message) {
        super(message);
    }
}
