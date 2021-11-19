package com.mercadolibre.samples.items.domain.exceptions;

/**
 * The item error.
 * Created by jmejia on 16/11/21.
 */
public class InvalidItemException extends RuntimeException {
    public InvalidItemException(final String message) {
        super(message);
    }
}
