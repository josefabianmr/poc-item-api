package com.mercadolibre.samples.items.infrastructure.spark.repositories;

import com.mercadolibre.samples.items.domain.entities.Item;
import com.mercadolibre.samples.items.domain.repositories.ItemRepository;
import com.mercadolibre.samples.items.domain.vo.Price;
import com.mercadolibre.samples.items.domain.vo.Site;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import javax.inject.Singleton;

/**
 * This adapter implements {@link ItemRepository}.
 * Created by jmejia on 21/11/21.
 */
@Singleton
public class RestItemAdapter implements ItemRepository {

    @Override
    public CompletionStage<Item> findItemByIdAndSellerId(final String itemId, final String sellerId) {
        return CompletableFuture.supplyAsync(() -> {
            final var item = Item.create("01", "Test", "001");
            item.updateSite(Site.MCO);
            item.updatePrice(Price.buildDefault());
            return item;
        });
    }

    @Override
    public void persist(final Item item) {

    }
}
