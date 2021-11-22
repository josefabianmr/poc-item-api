package com.mercadolibre.samples.items.infrastructure.spark.rest;

import static spark.Spark.get;

import com.google.gson.Gson;
import com.google.inject.Guice;
import com.mercadolibre.samples.items.application.usecases.ItemUseCases;
import com.mercadolibre.samples.items.infrastructure.entrypoints.contracts.FindItemRequest;
import com.mercadolibre.samples.items.infrastructure.entrypoints.handlers.ItemEntryPointHandler;
import com.mercadolibre.samples.items.infrastructure.spark.config.ModuleConfig;
import spark.Request;

/**
 * This controller allows handling requests for handling items.
 * Created by jmejia on 17/11/21.
 */
public class SparkItemRouterHandler {

    public static void main(final String[] args) {
        final var itemEntryPointHandler = buildEntryPoint();

        get("/items/:itemId/sellers/:sellerId", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                itemEntryPointHandler.findItem(buildRequest(request)).toCompletableFuture().get()
            );
        });
    }

    private static ItemEntryPointHandler buildEntryPoint() {
        final var injector = Guice.createInjector(new ModuleConfig());
        return new ItemEntryPointHandler(injector.getInstance(ItemUseCases.class));
    }

    private static FindItemRequest buildRequest(final Request request) {
        return new FindItemRequest(request.params(":itemId"), request.params(":sellerId"));
    }
}
