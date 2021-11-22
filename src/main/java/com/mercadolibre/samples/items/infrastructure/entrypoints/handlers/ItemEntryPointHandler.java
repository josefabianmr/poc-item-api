package com.mercadolibre.samples.items.infrastructure.entrypoints.handlers;

import com.google.gson.Gson;
import com.mercadolibre.samples.items.application.usecases.ItemUseCases;
import com.mercadolibre.samples.items.domain.entities.Item;
import com.mercadolibre.samples.items.infrastructure.entrypoints.contracts.FindItemRequest;
import com.mercadolibre.samples.items.infrastructure.entrypoints.contracts.FindItemResponse;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Description.
 * Created by jmejia on 17/11/21.
 */
@Slf4j
@AllArgsConstructor
public class ItemEntryPointHandler {

    final ItemUseCases itemUseCases;

    public CompletionStage<FindItemResponse> findItem(final FindItemRequest request) {
        log.info("Finding item with id:{} for seller:{}", request.getItemId(), request.getSellerId());
        return itemUseCases.findItem(request.getItemId(), request.getSellerId())
            .thenApply(item -> {
                log.info("The item with id:{} was found, response: {}", request.getItemId(), new Gson().toJson(item));
                return buildResponseFromItem(item);
            });
    }

    private FindItemResponse buildResponseFromItem(final Item item) {
        return FindItemResponse.builder()
            .id(item.getId())
            .title(item.getTitle())
            .categoryId(item.getCategoryId())
            .price(item.getPrice().getValue())
            .currency(item.getPrice().getCurrency())
            .site(item.getSite())
            .status(item.getStatus())
            .attributes(item.getAttributes().stream().map(attribute ->
                    FindItemResponse.Attribute.builder()
                        .id(attribute.getId())
                        .name(attribute.getName())
                        .valueId(attribute.getValueId())
                        .valueName(attribute.getValueName()).build()
                ).collect(Collectors.toList())
            ).build();
    }
}
