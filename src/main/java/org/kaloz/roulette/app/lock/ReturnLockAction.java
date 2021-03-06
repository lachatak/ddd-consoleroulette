package org.kaloz.roulette.app.lock;

import org.kaloz.roulette.domain.RouletteGame;

public abstract class ReturnLockAction<T> {

    protected abstract T apply(final RouletteGame rouletteGame);

}
