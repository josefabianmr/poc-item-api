package com.mercadolibre.samples.items.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The address values.
 * Created by jmejia on 16/11/21.
 */
@Getter
@AllArgsConstructor
public class Address {
    private final Country country;

    private final City city;
}
