package org.kaloz.roulette.infrastucture.adapters.driven.persistence;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.kaloz.roulette.domain.core.RouletteGameId.rouletteGameId;
import static org.kaloz.roulette.domain.TestFixtures.ROULETTE_GAME;
import static org.kaloz.roulette.domain.TestFixtures.VALID_GAME_ID;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kaloz.roulette.domain.RouletteGame;
import org.kaloz.roulette.domain.exception.RouletteGameNotFoundException;
import org.kaloz.roulette.domain.core.RouletteGameId;
import org.kaloz.roulette.infrastucture.adapters.driven.persistence.assembler.PersistenceRouletteGameAssembler;
import org.kaloz.roulette.infrastucture.adapters.driven.persistence.data.PersistenceRouletteGame;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RouletteGameRepositoryImplUTest {

    @Mock
    private RouletteGameStore rouletteGameStore;

    @Mock
    private PersistenceRouletteGameAssembler persistenceRouletteGameAssembler;

    @Mock
    private PersistenceRouletteGame persistenceRouletteGame;

    @Mock
    private RouletteGame rouletteGame;

    @InjectMocks
    private RouletteGameRepositoryImpl rouletteRepository;

    @Test(expected = NullPointerException.class)
    public void storeWithNullRouletteGameShouldThrowException() {
        // act
        rouletteRepository.store(null);
    }

    @Test
    public void storeWithValidRouletteGameShouldReturnRouletteGameId() {
        // setup
        when(persistenceRouletteGameAssembler.assemble(any(RouletteGame.class))).thenReturn(persistenceRouletteGame);
        when(rouletteGameStore.store(persistenceRouletteGame)).thenReturn(VALID_GAME_ID);

        // act
        RouletteGameId rouletteGameId = rouletteRepository.store(ROULETTE_GAME);

        // assert
        assertThat(rouletteGameId, equalTo(rouletteGameId(VALID_GAME_ID)));
    }

    @Test(expected = RouletteGameNotFoundException.class)
    public void loadGameDoesNotExistsShouldThrowException() {
        // setup
        when(rouletteGameStore.load()).thenReturn(Optional.<PersistenceRouletteGame> absent());

        // act
        rouletteRepository.load();
    }

    @Test
    public void loadWithValidRouletteGameShouldCallTheStore() {
        // setup
        when(rouletteGameStore.load()).thenReturn(Optional.of(persistenceRouletteGame));
        when(persistenceRouletteGameAssembler.disassemble(any(PersistenceRouletteGame.class))).thenReturn(rouletteGame);

        // act
        RouletteGame result = rouletteRepository.load();

        // assert
        assertThat(result, equalTo(rouletteGame));
    }

    @Test(expected = NullPointerException.class)
    public void updateWithNullRouletteGameShouldThrowException() {
        // act
        rouletteRepository.update(null);
    }

    @Test
    public void updateWithValidRouletteGameShouldCallTheStore() {
        // setup
        when(persistenceRouletteGameAssembler.assemble(any(RouletteGame.class))).thenReturn(persistenceRouletteGame);

        // act
        rouletteRepository.update(ROULETTE_GAME);

        // assert
        verify(rouletteGameStore).update(persistenceRouletteGame);
    }

}