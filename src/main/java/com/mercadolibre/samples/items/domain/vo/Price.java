package com.mercadolibre.samples.items.domain.vo;

import com.mercadolibre.samples.items.domain.exceptions.InvalidAmountException;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The prices value.
 * Created by jmejia on 16/11/21.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Price {

    private final BigDecimal value;

    private final Currency currency;

    public static Price create(final BigDecimal value, final Currency currency) {
        validate(value);
        return new Price(value, currency);
    }

    public static void validate(final BigDecimal value) {
        if (BigDecimal.ZERO.compareTo(value) > 0) {
            throw new InvalidAmountException("The amount must be greater than zero");
        }
    }

    public static Price buildDefault() {
        return new Price(BigDecimal.ZERO, Currency.ARS);
    }
}