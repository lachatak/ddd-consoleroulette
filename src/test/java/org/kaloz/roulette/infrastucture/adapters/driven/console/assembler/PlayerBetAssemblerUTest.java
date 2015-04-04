package org.kaloz.roulette.infrastucture.adapters.driven.console.assembler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.kaloz.roulette.domain.core.TestFixtures.INVALID_PLAYER_INPUT;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_PLAYER_BET_PLAYER1;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_PLAYER_INPUT;

import org.junit.Test;
import org.kaloz.roulette.domain.core.PlayerBet;

public class PlayerBetAssemblerUTest {

    private final PlayerBetAssembler testObj = new PlayerBetAssembler();

    @Test
    public void assembleValidUserInputShouldReturnPlayerBet() {
        // act
        PlayerBet result = testObj.assemble(VALID_PLAYER_INPUT);

        // assert
        assertThat(result, equalTo(VALID_PLAYER_BET_PLAYER1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void assembleInValidUserInputShouldThrowException() {
        // act
        testObj.assemble(INVALID_PLAYER_INPUT);
    }

}