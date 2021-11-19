package com.mercadolibre.samples.items.application.usecases;

import com.mercadolibre.samples.items.domain.aggregator.ItemAggregator;
import com.mercadolibre.samples.items.domain.entities.Item;
import com.mercadolibre.samples.items.domain.repositories.ItemRepository;
import java.util.concurrent.CompletionStage;
import lombok.AllArgsConstructor;

/**
 * The use cases for items.
 * Created by jmejia on 16/11/21.
 */
@AllArgsConstructor
public class ItemUseCases {

    private final ItemRepository itemRepository;

    public CompletionStage<Item> findItem(final String itemId, final String sellerId) {
        return ItemAggregator.load(itemRepository, itemId, sellerId).thenApply(ItemAggregator::getItem);
    }
}