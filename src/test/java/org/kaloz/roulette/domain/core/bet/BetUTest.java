package org.kaloz.roulette.domain.core.bet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.kaloz.roulette.domain.core.bet.Bet.bet;
import static org.kaloz.roulette.domain.core.bet.field.EvenField.evenField;
import static org.kaloz.roulette.domain.core.bet.field.NumberField.numberField;
import static org.kaloz.roulette.domain.core.bet.field.OddField.oddField;

import java.math.BigDecimal;

import org.junit.Test;
import org.kaloz.roulette.domain.core.bet.Bet;
import org.kaloz.roulette.domain.core.bet.field.EvenField;
import org.kaloz.roulette.domain.core.bet.field.NumberField;
import org.kaloz.roulette.domain.core.bet.field.OddField;

public class BetUTest {

    @Test
    public void createBetsWithValidNumberFieldAndAmountShouldReturnBet() {
        // act
        Bet bet = bet("2", "2");

        // assert
        assertThat((NumberField) bet.getField(), equalTo(numberField(2)));
        assertThat(bet.getAmount(), closeTo(new BigDecimal(2), BigDecimal.ZERO));
    }

    @Test
    public void createBetsWithValidOddFieldAndAmountShouldReturnBet() {
        // act
        Bet bet = bet("ODD", "2");

        // assert
        assertThat((OddField) bet.getField(), equalTo(oddField()));
        assertThat(bet.getAmount(), closeTo(new BigDecimal(2), BigDecimal.ZERO));
    }

    @Test
    public void createBetsWithValidEvenFieldAndAmountShouldReturnBet() {
        // act
        Bet bet = bet("EVEN", "2");

        // assert
        assertThat((EvenField) bet.getField(), equalTo(evenField()));
        assertThat(bet.getAmount(), closeTo(new BigDecimal(2), BigDecimal.ZERO));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createBetsWithInvalidFieldAndAmountShouldReturnAnException() {
        // act
        bet("NOTDEFINED", "2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createBetsWithEmptyFieldAndAmountShouldReturnAnException() {
        // act
        bet("", "2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createBetsWithValidFieldAndNegativeAmountShouldReturnAnException() {
        // act
        bet("2", "-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void createBetsWithValidFieldAndInvalidAmountShouldReturnAnException() {
        // act
        bet("2", "INVALID");
    }
}