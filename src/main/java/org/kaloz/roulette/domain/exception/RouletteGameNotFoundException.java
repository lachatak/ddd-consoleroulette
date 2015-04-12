package org.kaloz.roulette.domain.exception;

public class RouletteGameNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Roulette game not found!!";

    public RouletteGameNotFoundException() {
        super(MESSAGE);
    }
}
