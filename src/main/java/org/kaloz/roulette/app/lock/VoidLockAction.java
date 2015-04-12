package org.kaloz.roulette.app.lock;

import org.kaloz.roulette.domain.RouletteGame;

public abstract class VoidLockAction {

    protected abstract void apply(final RouletteGame rouletteGame);

}
