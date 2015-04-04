package org.kaloz.roulette.domain.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.kaloz.roulette.domain.core.PlayerPosition.playerPosition;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_PLAYER1;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_TOTAL_BET_1;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_TOTAL_BET_2;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_TOTAL_WIN_1;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_TOTAL_WIN_2;
import static org.kaloz.roulette.domain.core.TotalBet.totalBet;
import static org.kaloz.roulette.domain.core.TotalWin.totalWin;

import java.math.BigDecimal;

import org.junit.Test;

public class PlayerPositionUTest {

    @Test(expected = NullPointerException.class)
    public void createWithNullPlayerShouldThrowException() {
        // act
        playerPosition(null);
    }

    @Test(expected = NullPointerException.class)
    public void createWithNullTotalWinShouldThrowException() {
        // act
        playerPosition(VALID_PLAYER1, null, VALID_TOTAL_WIN_1);
    }

    @Test(expected = NullPointerException.class)
    public void createWithNullTotalBetShouldThrowException() {
        // act
        playerPosition(VALID_PLAYER1, VALID_TOTAL_WIN_1, null);
    }

    @Test
    public void addShouldReturnPlayerPosition() {
        // setup
        PlayerPosition playerPosition = playerPosition(VALID_PLAYER1, VALID_TOTAL_WIN_1, VALID_TOTAL_BET_1);

        // act
        PlayerPosition result = playerPosition.add(BigDecimal.ONE, BigDecimal.ONE);
        // assert
        assertThat(result.getTotalWin(), equalTo(totalWin(VALID_TOTAL_WIN_2)));
        assertThat(result.getTotalBet(), equalTo(totalBet(VALID_TOTAL_BET_2)));
    }
}