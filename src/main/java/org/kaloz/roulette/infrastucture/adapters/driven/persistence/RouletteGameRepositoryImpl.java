package org.kaloz.roulette.infrastucture.adapters.driven.persistence;

import static org.kaloz.roulette.domain.core.RouletteGameId.rouletteGameId;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.Validate;
import org.kaloz.roulette.domain.RouletteGame;
import org.kaloz.roulette.domain.RouletteGameNotFoundException;
import org.kaloz.roulette.domain.RouletteGameRepository;
import org.kaloz.roulette.domain.core.RouletteGameId;
import org.kaloz.roulette.infrastucture.adapters.driven.persistence.assembler.PersistenceRouletteGameAssembler;
import org.kaloz.roulette.infrastucture.adapters.driven.persistence.data.PersistenceRouletteGame;

import com.google.common.base.Optional;

@Named
@Slf4j
public class RouletteGameRepositoryImpl implements RouletteGameRepository {

    private final RouletteGameStore rouletteGameStore;
    private final PersistenceRouletteGameAssembler persistenceRouletteGameAssembler;

    @Inject
    public RouletteGameRepositoryImpl(final RouletteGameStore rouletteGameStore, final PersistenceRouletteGameAssembler persistenceRouletteGameAssembler) {
        this.rouletteGameStore = rouletteGameStore;
        this.persistenceRouletteGameAssembler = persistenceRouletteGameAssembler;
    }

    public RouletteGameId store(final RouletteGame rouletteGame) {
        Validate.notNull(rouletteGame, "RouletteGame cannot be null!");

        PersistenceRouletteGame persistenceRouletteGame = persistenceRouletteGameAssembler.assemble(rouletteGame);
        String id = rouletteGameStore.store(persistenceRouletteGame);
        log.debug("{} has been created!", persistenceRouletteGame);
        return rouletteGameId(id);
    }

    public RouletteGame load() {
        Optional<PersistenceRouletteGame> rouletteGameOptional = rouletteGameStore.load();
        if (rouletteGameOptional.isPresent()) {
            RouletteGame rouletteGame = persistenceRouletteGameAssembler.disassemble(rouletteGameOptional.get());
            log.debug("{} has been loaded!", rouletteGame);
            return rouletteGame;
        } else {
            throw new RouletteGameNotFoundException("Roulette game not found!!");
        }
    }

    public void update(final RouletteGame rouletteGame) {
        Validate.notNull(rouletteGame, "RouletteGame cannot be null!");

        PersistenceRouletteGame persistenceRouletteGame = persistenceRouletteGameAssembler.assemble(rouletteGame);
        rouletteGameStore.update(persistenceRouletteGame);
        log.debug("{} has been updated!", persistenceRouletteGame);
    }
}
