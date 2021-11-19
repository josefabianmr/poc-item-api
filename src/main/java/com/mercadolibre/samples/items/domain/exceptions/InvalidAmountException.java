package com.mercadolibre.samples.items.domain.exceptions;

/**
 * The amount error.
 * Created by jmejia on 16/11/21.
 */
public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(final String message) {
        super(message);
    }
}
