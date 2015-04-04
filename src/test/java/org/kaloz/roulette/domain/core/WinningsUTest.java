package org.kaloz.roulette.domain.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.kaloz.roulette.domain.core.TestFixtures.VALID_WINNINGS_2;
import static org.kaloz.roulette.domain.core.Winnings.winnings;

import java.math.BigDecimal;

import org.junit.Test;

public class WinningsUTest {
    @Test(expected = NullPointerException.class)
    public void createWithNullWinningsShouldThrowException() {
        // act
        winnings(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithWinningsShouldThrowException() {
        // act
        winnings(new BigDecimal(-1));
    }

    @Test
    public void createWithinTheValidRangeShouldReturnTotalBet() {
        // act
        Winnings result = winnings(VALID_WINNINGS_2);
        // assert
        assertThat(result.getAmount(), closeTo(VALID_WINNINGS_2, BigDecimal.ZERO));
    }
}