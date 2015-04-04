package org.kaloz.roulette.domain.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.kaloz.roulette.domain.core.Pocket.pocket;

import org.junit.Test;

public class PocketUTest {

    @Test(expected = IllegalArgumentException.class)
    public void createWithNegativeNumberShouldThrowException() {
        // act
        pocket(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithGreaterThan36ShouldThrowException() {
        // act
        pocket(37);
    }

    @Test
    public void createWithinTheValidRangeShouldReturnPocket() {
        // act
        Pocket result = pocket(20);
        // assert
        assertThat(result.getNumber(), equalTo(20));
    }
}