package org.kaloz.roulette.domain.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.kaloz.roulette.domain.core.Player.player;
import static org.kaloz.roulette.domain.TestFixtures.EMPTY_PLAYER_NAME;
import static org.kaloz.roulette.domain.TestFixtures.VALID_PLAYER_NAME_PLAYER1;

import org.junit.Test;

public class PlayerUTest {

    @Test(expected = NullPointerException.class)
    public void createWithNullStringShouldThrowException() {
        // act
        player(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithEmptyStringShouldThrowException() {
        // act
        player(EMPTY_PLAYER_NAME);
    }

    @Test
    public void createWithValidStringShouldReturnPlayer() {
        // act
        Player result = player(VALID_PLAYER_NAME_PLAYER1);

        // assert
        assertThat(result.getName(), equalTo(VALID_PLAYER_NAME_PLAYER1));
    }
}