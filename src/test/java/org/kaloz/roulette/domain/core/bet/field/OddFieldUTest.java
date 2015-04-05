package org.kaloz.roulette.domain.core.bet.field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.kaloz.roulette.domain.TestFixtures.VALID_MULTIPLIER_2;
import static org.kaloz.roulette.domain.core.bet.field.OddField.oddField;

import java.math.BigDecimal;

import org.junit.Test;

public class OddFieldUTest {

    @Test
    public void createOddFieldShouldReturnOddField() {
        // act
        OddField oddField = oddField();

        // assert
        assertThat(oddField.getPockets(), contains(1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35));
        assertThat(oddField.getPockets(), hasSize(18));
        assertThat(oddField.getMultiplier(), closeTo(VALID_MULTIPLIER_2, BigDecimal.ZERO));
    }
}