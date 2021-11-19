package com.mercadolibre.samples.items.domain.aggregator;

import com.mercadolibre.samples.items.domain.entities.Item;
import com.mercadolibre.samples.items.domain.exceptions.InvalidItemException;
import com.mercadolibre.samples.items.domain.repositories.ItemRepository;
import com.mercadolibre.samples.items.domain.vo.Attribute;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletionStage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * The item aggregator.
 * Created by jmejia on 16/11/21.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemAggregator {

    private final ItemRepository repository;

    private final String sellerId;

    private Item item;

    public static ItemAggregator create(final ItemRepository repository, final String sellerId) {
        return new ItemAggregator(repository, sellerId, null);
    }

    public static CompletionStage<ItemAggregator> load(final ItemRepository repository, final String itemId, final String sellerId) {
        return repository.findItemByIdAndSellerId(itemId, sellerId)
            .thenApply(item -> {
                var aggregator = create(repository, sellerId);
                aggregator.item = item;
                return aggregator;
            });
    }

    public void addItem(final String id, final String title, final String categoryId) {
        this.item = Item.create(id, title, categoryId);
    }

    public void addItemAttributes(final List<Attribute> attributes) {
        attributes.forEach(item::addAttribute);
    }

    public void persistItem() {
        validateItem();
        repository.persist(item);
    }

    public Item getItem() {
        return item;
    }

    public String getSellerId() {
        return sellerId;
    }

    private void validateItem() {
        if (Objects.isNull(item)) {
            throw new InvalidItemException("The item cannot be null");
        }
    }
}
