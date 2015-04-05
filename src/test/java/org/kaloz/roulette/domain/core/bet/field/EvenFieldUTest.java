package org.kaloz.roulette.domain.core.bet.field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.kaloz.roulette.domain.TestFixtures.VALID_MULTIPLIER_2;
import static org.kaloz.roulette.domain.core.bet.field.EvenField.evenField;

import java.math.BigDecimal;

import org.junit.Test;

public class EvenFieldUTest {

    @Test
    public void createEvenFieldShouldReturnEvenField() {
        // act
        EvenField evenField = evenField();

        // assert
        assertThat(evenField.getPockets(), contains(2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36));
        assertThat(evenField.getPockets(), hasSize(18));
        assertThat(evenField.getMultiplier(), closeTo(VALID_MULTIPLIER_2, BigDecimal.ZERO));
    }
}