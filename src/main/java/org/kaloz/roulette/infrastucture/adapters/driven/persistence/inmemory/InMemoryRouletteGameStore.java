package org.kaloz.roulette.infrastucture.adapters.driven.persistence.inmemory;

import java.util.UUID;

import org.kaloz.roulette.infrastucture.adapters.driven.persistence.RouletteGameStore;
import org.kaloz.roulette.infrastucture.adapters.driven.persistence.data.PersistenceRouletteGame;

import com.google.common.base.Optional;

public class InMemoryRouletteGameStore implements RouletteGameStore {

    private Optional<PersistenceRouletteGame> persistenceRouletteGame = Optional.absent();

    @Override
    public String store(PersistenceRouletteGame persistenceRouletteGame) {
        String persistenceRouletteGameId = UUID.randomUUID().toString();
        persistenceRouletteGame.setId(persistenceRouletteGameId);
        this.persistenceRouletteGame = Optional.of(persistenceRouletteGame);
        return persistenceRouletteGameId;
    }

    @Override
    public Optional<PersistenceRouletteGame> load() {
        return persistenceRouletteGame;
    }

    @Override
    public void update(PersistenceRouletteGame persistenceRouletteGame) {
        this.persistenceRouletteGame = Optional.of(persistenceRouletteGame);
    }
}
