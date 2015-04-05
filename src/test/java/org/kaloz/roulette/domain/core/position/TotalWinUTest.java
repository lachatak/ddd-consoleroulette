package org.kaloz.roulette.domain.core.position;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.kaloz.roulette.domain.TestFixtures.VALID_TOTAL_WIN_2;
import static org.kaloz.roulette.domain.core.position.TotalWin.totalWin;

import java.math.BigDecimal;

import org.junit.Test;

public class TotalWinUTest {

    @Test(expected = NullPointerException.class)
    public void createWithNullTotalWinShouldThrowException() {
        // act
        totalWin(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithNegativeWinShouldThrowException() {
        // act
        totalWin(new BigDecimal(-1));
    }

    @Test
    public void createWithinTheValidRangeShouldReturnTotalBet() {
        // act
        TotalWin result = totalWin(VALID_TOTAL_WIN_2);
        // assert
        assertThat(result.getAmount(), closeTo(VALID_TOTAL_WIN_2, BigDecimal.ZERO));
    }
}