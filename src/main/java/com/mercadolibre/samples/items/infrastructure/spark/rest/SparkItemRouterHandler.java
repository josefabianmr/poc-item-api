package com.mercadolibre.samples.items.infrastructure.spark.rest;

import static spark.Spark.get;

import com.google.gson.Gson;
import com.mercadolibre.samples.items.infrastructure.entrypoints.contracts.FindItemRequest;
import com.mercadolibre.samples.items.infrastructure.entrypoints.handlers.ItemEntryPointHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This controller allows handling requests for handling items.
 * Created by jmejia on 17/11/21.
 */
@Component
@AllArgsConstructor
public class SparkItemRouterHandler {

    private final ItemEntryPointHandler itemEntryPointHandler;

    public static void main(final String[] args) {
        get("/items/:itemId/sellers/:sellerId", (request, response) -> {
            response.type("application/json");
            return new Gson().toJson(
                itemEntryPointHandler.findItem(new FindItemRequest(request.params(":itemId"), request.params(":sellerId")))
            );
        });
    }
}
