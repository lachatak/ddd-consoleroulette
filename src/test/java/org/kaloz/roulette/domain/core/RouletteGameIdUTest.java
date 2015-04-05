package org.kaloz.roulette.domain.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.kaloz.roulette.domain.core.RouletteGameId.rouletteGameId;
import static org.kaloz.roulette.domain.core.TestFixtures.EMPTY_GAME_ID;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_GAME_ID;

import org.junit.Test;

public class RouletteGameIdUTest {

    @Test(expected = NullPointerException.class)
    public void createWithNullStringShouldThrowException() {
        // act
        rouletteGameId(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithEmptyStringShouldThrowException() {
        // act
        rouletteGameId(EMPTY_GAME_ID);
    }

    @Test
    public void createWithValidStringShouldReturnPlayer() {
        // act
        RouletteGameId result = rouletteGameId(VALID_GAME_ID);

        // assert
        assertThat(result.getId(), equalTo(VALID_GAME_ID));
    }

}