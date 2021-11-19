package com.mercadolibre.samples.items.infrastructure.entrypoints.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.samples.items.domain.vo.Currency;
import com.mercadolibre.samples.items.domain.vo.ItemStatus;
import com.mercadolibre.samples.items.domain.vo.Site;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This contract is returned for the query of an item.
 * Created by jmejia on 17/11/21.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindItemResponse {

    private String id;

    private String title;

    private BigDecimal price;

    private Currency currency;

    private Site site;

    @JsonProperty("category_id")
    private String categoryId;

    private List<Attribute> attributes;

    private ItemStatus status;


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Attribute {
        private String id;

        private String name;

        private String valueId;

        private String valueName;
    }
}
