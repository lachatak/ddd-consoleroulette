package org.kaloz.roulette.domain.core.bet.field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.kaloz.roulette.domain.TestFixtures.VALID_MULTIPLIER_36;
import static org.kaloz.roulette.domain.core.bet.field.NumberField.numberField;

import java.math.BigDecimal;

import org.junit.Test;

public class NumberFieldUTest {

    @Test
    public void createNumberFieldWithValidNumberShouldReturnNumberField() {
        // act
        NumberField numberField = numberField(2);

        // assert
        assertThat(numberField.getPockets(), contains(2));
        assertThat(numberField.getPockets(), hasSize(1));
        assertThat(numberField.getMultiplier(), closeTo(VALID_MULTIPLIER_36, BigDecimal.ZERO));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNumberFieldWithNegativeNumberShouldReturnThrowException() {
        // act
        numberField(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNumberFieldWithNumberGreaterThan36ShouldReturnThrowException() {
        // act
        numberField(67);
    }
}