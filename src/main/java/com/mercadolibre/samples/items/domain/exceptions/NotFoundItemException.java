package com.mercadolibre.samples.items.domain.exceptions;

/**
 * Item was not found.
 * Created by jmejia on 21/11/21.
 */
public class NotFoundItemException extends RuntimeException {
    public NotFoundItemException(final String itemId) {
        super(String.format("The item with id:%s was not found", itemId));
    }
}
