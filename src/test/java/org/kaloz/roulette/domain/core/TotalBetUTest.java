package org.kaloz.roulette.domain.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_TOTAL_BET_1;
import static org.kaloz.roulette.domain.core.TotalBet.totalBet;

import java.math.BigDecimal;

import org.junit.Test;

public class TotalBetUTest {

    @Test(expected = NullPointerException.class)
    public void createWithNullTotalBetShouldThrowException() {
        // act
        totalBet(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNegativeBetShouldThrowException() {
        // act
        totalBet(new BigDecimal(-1));
    }

    @Test
    public void createWithinTheValidRangeShouldReturnTotalBet() {
        // act
        TotalBet result = totalBet(VALID_TOTAL_BET_1);
        // assert
        assertThat(result.getAmount(), closeTo(VALID_TOTAL_BET_1, BigDecimal.ZERO));
    }

}