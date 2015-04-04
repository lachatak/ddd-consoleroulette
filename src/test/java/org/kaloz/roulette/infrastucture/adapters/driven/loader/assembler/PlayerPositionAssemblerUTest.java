package org.kaloz.roulette.infrastucture.adapters.driven.loader.assembler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.kaloz.roulette.domain.core.TestFixtures.INVALID_LOAD_INPUT_AMOUNT;
import static org.kaloz.roulette.domain.core.TestFixtures.INVALID_LOAD_INPUT_FIELD;
import static org.kaloz.roulette.domain.core.TestFixtures.INVALID_LOAD_INPUT_LESS_FIELDS;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_LOAD_INPUT;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_ZERO_PLAYER_POSITION_PLAYER1;

import org.junit.Test;
import org.kaloz.roulette.domain.core.PlayerPosition;

public class PlayerPositionAssemblerUTest {

    private final PlayerPositionAssembler testObj = new PlayerPositionAssembler();

    @Test
    public void assembleValidUserInputShouldReturnPlayerBet() {
        // act
        PlayerPosition result = testObj.assemble(VALID_LOAD_INPUT);

        // assert
        assertThat(result, equalTo(VALID_ZERO_PLAYER_POSITION_PLAYER1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void assembleInValidUserInputLessFieldsShouldThrowException() {
        // act
        testObj.assemble(INVALID_LOAD_INPUT_LESS_FIELDS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void assembleInValidUserInputAmountShouldThrowException() {
        // act
        testObj.assemble(INVALID_LOAD_INPUT_AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void assembleInValidUserInputFieldShouldThrowException() {
        // act
        testObj.assemble(INVALID_LOAD_INPUT_FIELD);
    }
}