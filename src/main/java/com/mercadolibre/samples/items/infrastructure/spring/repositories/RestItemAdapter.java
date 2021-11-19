package com.mercadolibre.samples.items.infrastructure.spring.repositories;

import com.mercadolibre.samples.items.domain.entities.Item;
import com.mercadolibre.samples.items.domain.repositories.ItemRepository;
import com.mercadolibre.samples.items.domain.vo.Attribute;
import com.mercadolibre.samples.items.domain.vo.Currency;
import com.mercadolibre.samples.items.domain.vo.Price;
import com.mercadolibre.samples.items.domain.vo.Site;
import com.mercadolibre.samples.items.infrastructure.spring.repositories.contracts.ItemResponse;
import java.util.concurrent.CompletionStage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * This adapter implements the item repository from rest.
 * Created by jmejia on 17/11/21.
 */
@Slf4j
public class RestItemAdapter implements ItemRepository {

    private static final String GET_ITEM_PATH = "https://internal-api.mercadolibre.com/items/";

    private final WebClient webClient = WebClient.builder().baseUrl(GET_ITEM_PATH).build();

    @Override
    public CompletionStage<Item> findItemByIdAndSellerId(final String itemId, final String sellerId) {
        log.info("Finding item with id:{} for seller:{}", itemId, sellerId);
        return webClient.get()
            .uri(itemId)
            .exchangeToMono(clientResponse -> {
                log.info("A successful response was received obtaining the item: {} from the items api", itemId);
                return clientResponse.bodyToMono(ItemResponse.class);
            }).map(itemResponse -> {
                var item = Item.create(itemResponse.getId(), itemResponse.getTitle(), itemResponse.getCategoryId());
                item.updatePrice(Price.create(itemResponse.getPrice(), Currency.valueOf(itemResponse.getCurrencyId())));
                item.updateSite(Site.valueOf(itemResponse.getSite()));
                itemResponse.getAttributes().forEach(attribute -> item.addAttribute(Attribute.builder()
                    .id(attribute.getId())
                    .name(attribute.getName())
                    .valueId(attribute.getValueId())
                    .valueName(attribute.getValueName())
                    .build()));
                return item;
            }).toFuture();
    }

    @Override
    public void persist(final Item item) {

    }
}
