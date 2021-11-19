package com.mercadolibre.samples.items.infrastructure.spark.config;

import com.google.inject.AbstractModule;
import com.mercadolibre.samples.items.domain.repositories.ItemRepository;
import com.mercadolibre.samples.items.infrastructure.spring.repositories.RestItemAdapter;

/**
 * The module configuration.
 * Created by jmejia on 16/11/21.
 */
public class ModuleConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(ItemRepository.class).to(RestItemAdapter)
    }
}
