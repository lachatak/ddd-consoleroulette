package org.kaloz.roulette.domain.core.bet.field;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.ImmutableList;

@Data
public abstract class Field {

    protected ImmutableList<Integer> pockets;
    private BigDecimal multiplier;

    protected Field(final List<Integer> pockets, final BigDecimal multiplier) {
        Validate.notNull(pockets, "Pockets cannot be null!");
        Validate.isTrue(pockets.size() > 0, "Pockets cannot be empty!");
        Validate.isTrue(multiplier.signum() >= 0, "Multiplier must be greater then 0!");
        this.pockets = ImmutableList.copyOf(pockets);
        this.multiplier = multiplier;
    }

    public boolean contains(final int number) {
        return pockets.contains(number);
    }

    public abstract String bet();
}
