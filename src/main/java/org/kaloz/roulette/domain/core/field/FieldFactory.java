package org.kaloz.roulette.domain.core.field;

import static org.kaloz.roulette.domain.core.field.EvenField.evenField;
import static org.kaloz.roulette.domain.core.field.NumberField.numberField;
import static org.kaloz.roulette.domain.core.field.OddField.oddField;

import org.apache.commons.lang3.Validate;

public class FieldFactory {

    private enum Fields {
        EVEN, ODD
    }

    public static Field createField(final String field) {

        Validate.notBlank(field, "Field cannot be empty!");
        try {
            return numberField(Integer.parseInt(field));
        } catch (NumberFormatException nfe) {
            if (Fields.EVEN.name().equals(field)) {
                return evenField();
            } else if (Fields.ODD.name().equals(field)) {
                return oddField();
            }
        }
        throw new IllegalArgumentException("Field must be between 0 and 36, EVEN or ODD!");
    }
}
