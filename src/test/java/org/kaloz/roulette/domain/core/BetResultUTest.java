package org.kaloz.roulette.domain.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.kaloz.roulette.domain.core.BetResult.betResult;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_PLAYER_BET_PLAYER1;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_POCKET_1;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_POCKET_10;
import static org.kaloz.roulette.domain.core.Winnings.winnings;

import java.math.BigDecimal;

import org.junit.Test;

public class BetResultUTest {

    public static final double NUMBER_WINNING_MULTIPLAYER = 36;

    @Test(expected = NullPointerException.class)
    public void createWithNullPlayerShouldThrowException() {
        // act
        betResult(null, VALID_POCKET_10);
    }

    @Test(expected = NullPointerException.class)
    public void createWithNullPocketShouldThrowException() {
        // act
        betResult(VALID_PLAYER_BET_PLAYER1, null);
    }

    @Test
    public void createWithValidPlayerBetAndPocketShouldReturnLoseBetResult() {
        // act
        BetResult result = betResult(VALID_PLAYER_BET_PLAYER1, VALID_POCKET_10);

        // assert
        assertThat(result.getOutCome(), equalTo(OutCome.LOSE));
        assertThat(result.getWinnings(), equalTo(Winnings.zero()));
        assertThat(result.getPlayerBet(), equalTo(VALID_PLAYER_BET_PLAYER1));
    }

    @Test
    public void createWithValidWinningPlayerBetAndPocketShouldReturnWinBetResult() {
        // act
        BetResult result = betResult(VALID_PLAYER_BET_PLAYER1, VALID_POCKET_1);

        // assert
        assertThat(result.getOutCome(), equalTo(OutCome.WIN));
        assertThat(result.getWinnings(), equalTo(winnings(new BigDecimal(NUMBER_WINNING_MULTIPLAYER))));
        assertThat(result.getPlayerBet(), equalTo(VALID_PLAYER_BET_PLAYER1));
    }
}