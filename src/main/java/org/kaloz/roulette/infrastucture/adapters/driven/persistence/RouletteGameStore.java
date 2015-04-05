package org.kaloz.roulette.infrastucture.adapters.driven.persistence;

import org.kaloz.roulette.infrastucture.adapters.driven.persistence.data.PersistenceRouletteGame;

import com.google.common.base.Optional;

public interface RouletteGameStore {

    String store(final PersistenceRouletteGame persistenceRouletteGame);

    Optional<PersistenceRouletteGame> load();

    void update(final PersistenceRouletteGame persistenceRouletteGame);
}
