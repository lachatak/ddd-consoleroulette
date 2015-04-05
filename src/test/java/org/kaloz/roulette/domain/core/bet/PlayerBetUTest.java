package org.kaloz.roulette.domain.core.bet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.kaloz.roulette.domain.core.bet.PlayerBet.playerBet;
import static org.kaloz.roulette.domain.TestFixtures.VALID_BET_1;
import static org.kaloz.roulette.domain.TestFixtures.VALID_PLAYER1;

import org.junit.Test;

public class PlayerBetUTest {

    @Test(expected = NullPointerException.class)
    public void createWithNullPlayerShouldThrowException() {
        // act
        playerBet(null, VALID_BET_1);
    }

    @Test(expected = NullPointerException.class)
    public void createWithNullBetShouldThrowException() {
        // act
        playerBet(VALID_PLAYER1, null);
    }

    @Test
    public void createWithValidPlayerAndBetShouldReturnPlayerBet() {
        // act
        PlayerBet result = playerBet(VALID_PLAYER1, VALID_BET_1);

        // assert
        assertThat(result.getPlayer(), equalTo(VALID_PLAYER1));
        assertThat(result.getBet(), equalTo(VALID_BET_1));
    }
}