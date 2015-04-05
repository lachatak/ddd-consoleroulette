package org.kaloz.roulette.infrastucture.adapters.driven.persistence.inmemory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kaloz.roulette.infrastucture.adapters.driven.persistence.data.PersistenceRouletteGame;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Optional;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryRouletteGameStoreUTest {

    @Mock
    public PersistenceRouletteGame persistenceRouletteGame_1;

    @Mock
    public PersistenceRouletteGame persistenceRouletteGame_2;

    private InMemoryRouletteGameStore testObj;

    @Before
    public void before() {
        testObj = new InMemoryRouletteGameStore();
    }

    @Test
    public void loadWithoutStoringShouldReturnAbsent() {
        // act
        Optional<PersistenceRouletteGame> persistenceRouletteGameOptional = testObj.load();

        // assert
        assertThat(persistenceRouletteGameOptional, equalTo(Optional.<PersistenceRouletteGame> absent()));
    }

    @Test
    public void loadShouldReturnPersistenceRouletteGame() {
        // setup
        testObj.store(persistenceRouletteGame_1);

        // act
        Optional<PersistenceRouletteGame> persistenceRouletteGameOptional = testObj.load();

        // assert
        assertThat(persistenceRouletteGameOptional.get(), equalTo(persistenceRouletteGame_1));
    }

    @Test
    public void updateShouldOverrideThePreviousPersistenceRouletteGame() {
        // setup
        testObj.store(persistenceRouletteGame_1);

        // act
        testObj.update(persistenceRouletteGame_2);
        Optional<PersistenceRouletteGame> persistenceRouletteGameOptional = testObj.load();

        // assert
        assertThat(persistenceRouletteGameOptional.get(), equalTo(persistenceRouletteGame_2));
    }
}