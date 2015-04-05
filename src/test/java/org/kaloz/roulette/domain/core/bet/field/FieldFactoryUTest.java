package org.kaloz.roulette.domain.core.bet.field;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.kaloz.roulette.domain.core.bet.field.EvenField.evenField;
import static org.kaloz.roulette.domain.core.bet.field.NumberField.numberField;
import static org.kaloz.roulette.domain.core.bet.field.OddField.oddField;

import org.junit.Test;

public class FieldFactoryUTest {

    @Test(expected = IllegalArgumentException.class)
    public void createFieldWithInvalidStringShouldThrowException() {
        // act
        FieldFactory.createField("INVALID");
    }

    @Test
    public void createFieldWithValidNumberStringShouldReturnWithNumberField() {
        // act
        Field result = FieldFactory.createField("4");

        // assert
        assertThat((NumberField) result, equalTo(numberField(4)));
    }

    @Test
    public void createFieldWithODDStringShouldReturnWithOddField() {
        // act
        Field result = FieldFactory.createField("ODD");

        // assert
        assertThat((OddField) result, equalTo(oddField()));
    }

    @Test
    public void createFieldWithEVENStringShouldReturnWithEvenField() {
        // act
        Field result = FieldFactory.createField("EVEN");

        // assert
        assertThat((EvenField) result, equalTo(evenField()));
    }
}