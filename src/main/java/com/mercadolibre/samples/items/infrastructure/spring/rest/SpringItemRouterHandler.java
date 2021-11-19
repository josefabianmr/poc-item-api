package com.mercadolibre.samples.items.infrastructure.spring.rest;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.mercadolibre.samples.items.infrastructure.entrypoints.contracts.FindItemRequest;
import com.mercadolibre.samples.items.infrastructure.entrypoints.contracts.FindItemResponse;
import com.mercadolibre.samples.items.infrastructure.entrypoints.handlers.ItemEntryPointHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * This controller allows handling requests for handling items.
 * Created by jmejia on 17/11/21.
 */
@Configuration
public class SpringItemRouterHandler {

    @Bean
    public RouterFunction<ServerResponse> findItemRouterFunction(final ItemEntryPointHandler itemEntryPointHandler) {
        return route(
            GET("/items/{itemId}/sellers/{sellerId}"),
            req -> ok().body(BodyInserters.fromPublisher(Mono
                .fromCompletionStage(itemEntryPointHandler
                    .findItem(new FindItemRequest(req.pathVariable("itemId"), req.pathVariable("sellerId")))), FindItemResponse.class)
            )
        );
    }
}
