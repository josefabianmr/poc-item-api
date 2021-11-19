package com.mercadolibre.samples.items.infrastructure.entrypoints.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This contract is received for get an item.
 * Created by jmejia on 17/11/21.
 */
@Getter
@AllArgsConstructor
public class FindItemRequest {

    private String itemId;

    private String sellerId;
}
