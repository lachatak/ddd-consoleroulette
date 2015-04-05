package org.kaloz.roulette.domain;

import org.kaloz.roulette.domain.core.RouletteGameId;

public interface RouletteGameRepository {

    RouletteGameId store(final RouletteGame rouletteGame);

    RouletteGame load();

    void update(final RouletteGame rouletteGame);
}
