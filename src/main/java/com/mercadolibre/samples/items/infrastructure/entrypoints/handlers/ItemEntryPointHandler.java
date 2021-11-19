package com.mercadolibre.samples.items.infrastructure.entrypoints.handlers;

import com.mercadolibre.samples.items.application.usecases.ItemUseCases;
import com.mercadolibre.samples.items.domain.entities.Item;
import com.mercadolibre.samples.items.infrastructure.entrypoints.contracts.FindItemRequest;
import com.mercadolibre.samples.items.infrastructure.entrypoints.contracts.FindItemResponse;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

/**
 * Description.
 * Created by jmejia on 17/11/21.
 */
@AllArgsConstructor
public class ItemEntryPointHandler {

    final ItemUseCases itemUseCases;

    public CompletionStage<FindItemResponse> findItem(final FindItemRequest request) {
        return itemUseCases.findItem(request.getItemId(), request.getSellerId())
            .thenApply(this::buildResponseFromItem);
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
