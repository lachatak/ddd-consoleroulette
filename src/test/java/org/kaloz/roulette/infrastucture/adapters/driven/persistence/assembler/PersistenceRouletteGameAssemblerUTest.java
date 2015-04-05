package org.kaloz.roulette.infrastucture.adapters.driven.persistence.assembler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.kaloz.roulette.domain.core.TestFixtures.PERSISTENCE_BETS_PLAYER1;
import static org.kaloz.roulette.domain.core.TestFixtures.PERSISTENCE_POSITION_PLAYER1;
import static org.kaloz.roulette.domain.core.TestFixtures.PERSISTENCE_ROULETTE_GAME;
import static org.kaloz.roulette.domain.core.TestFixtures.ROULETTE_GAME;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_GAME_ID;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_PLAYER_BET_PLAYER1;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_PLAYER_POSITION_PLAYER1_WIN;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_ROULETTE_ID;

import org.junit.Test;
import org.kaloz.roulette.domain.RouletteGame;
import org.kaloz.roulette.infrastucture.adapters.driven.persistence.data.PersistenceRouletteGame;

public class PersistenceRouletteGameAssemblerUTest {

    private PersistenceRouletteGameAssembler testObj = new PersistenceRouletteGameAssembler();

    @Test
    public void assemble() {
        // act
        PersistenceRouletteGame persistenceRouletteGame = testObj.assemble(ROULETTE_GAME);

        // assert
        assertThat(persistenceRouletteGame.getId(), equalTo(VALID_GAME_ID));
        assertThat(persistenceRouletteGame.getCurrentBets(), hasSize(1));
        assertThat(persistenceRouletteGame.getCurrentBets(), contains(PERSISTENCE_BETS_PLAYER1));
        assertThat(persistenceRouletteGame.getPlayerPositions(), hasSize(1));
        assertThat(persistenceRouletteGame.getPlayerPositions(), contains(PERSISTENCE_POSITION_PLAYER1));
    }

    @Test
    public void disassemble() {
        // act
        RouletteGame rouletteGame = testObj.disassemble(PERSISTENCE_ROULETTE_GAME);

        // assert
        assertThat(rouletteGame.getRouletteGameId(), equalTo(VALID_ROULETTE_ID));
        assertThat(rouletteGame.currentBets(), hasSize(1));
        assertThat(rouletteGame.currentBets(), contains(VALID_PLAYER_BET_PLAYER1));
        assertThat(rouletteGame.playerPositions(), hasSize(1));
        assertThat(rouletteGame.playerPositions(), contains(VALID_PLAYER_POSITION_PLAYER1_WIN));
    }

}