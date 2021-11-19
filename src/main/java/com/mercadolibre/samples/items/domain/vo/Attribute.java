package com.mercadolibre.samples.items.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * The attribute definition.
 * Created by jmejia on 16/11/21.
 */
@Getter
@Builder
@AllArgsConstructor
public class Attribute {

    private final String id;

    private final String name;

    private final String valueId;

    private final String valueName;
}
