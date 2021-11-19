package com.mercadolibre.samples.items.domain.repositories;

import com.mercadolibre.samples.items.domain.entities.Item;
import java.util.concurrent.CompletionStage;

/**
 * This abstraction exposes methods to manage item information.
 * Created by jmejia on 16/11/21.
 */
public interface ItemRepository {

    CompletionStage<Item> findItemByIdAndSellerId(String itemId, String sellerId);

    void persist(Item item);
}
