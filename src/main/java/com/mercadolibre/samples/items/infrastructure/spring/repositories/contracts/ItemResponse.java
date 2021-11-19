package com.mercadolibre.samples.items.infrastructure.spring.repositories.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The item response.
 * Created by jmejia on 16/11/21.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private String id;

    @JsonProperty("site_id")
    private String site;

    private String title;

    @JsonProperty("category_id")
    private String categoryId;

    private BigDecimal price;

    @JsonProperty("currency_id")
    private String currencyId;

    private String status;

    private List<Attribute> attributes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Attribute {

        private String id;

        private String name;

        @JsonProperty("value_id")
        private String valueId;

        @JsonProperty("value_name")
        private String valueName;
    }
}