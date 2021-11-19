package com.mercadolibre.samples.items.infrastructure.spring.config;

import com.mercadolibre.samples.items.application.usecases.ItemUseCases;
import com.mercadolibre.samples.items.domain.repositories.ItemRepository;
import com.mercadolibre.samples.items.infrastructure.entrypoints.handlers.ItemEntryPointHandler;
import com.mercadolibre.samples.items.infrastructure.spring.repositories.RestItemAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The bean configuration.
 * Created by jmejia on 17/11/21.
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public ItemRepository itemRepository() {
        return new RestItemAdapter();
    }

    @Bean
    public ItemUseCases itemUseCases(final ItemRepository repository) {
        return new ItemUseCases(repository);
    }

    @Bean
    public ItemEntryPointHandler itemEntryPointHandler(final ItemUseCases itemUseCases) {
        return new ItemEntryPointHandler(itemUseCases);
    }
}
