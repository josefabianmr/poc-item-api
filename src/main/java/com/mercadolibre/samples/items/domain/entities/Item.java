package com.mercadolibre.samples.items.domain.entities;

import com.mercadolibre.samples.items.domain.exceptions.InvalidCategoryException;
import com.mercadolibre.samples.items.domain.exceptions.InvalidTitleException;
import com.mercadolibre.samples.items.domain.vo.Attribute;
import com.mercadolibre.samples.items.domain.vo.ItemStatus;
import com.mercadolibre.samples.items.domain.vo.Price;
import com.mercadolibre.samples.items.domain.vo.Site;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * This entity allows to manage an item.
 * Created by jmejia on 16/11/21.
 */
@Getter
@AllArgsConstructor
public class Item {

    private String id;

    private String title;

    private Price price;

    private Site site;

    private String categoryId;

    private List<Attribute> attributes;

    private ItemStatus status;

    public static Item create(final String id, final String title, final String categoryId) {
        validateItem(categoryId, title);
        return new Item(id, title, Price.buildDefault(), null, categoryId, new ArrayList<>(), ItemStatus.INACTIVE);
    }

    public void updatePrice(final Price price) {
        this.price = price;
    }

    public void updateSite(final Site site) {
        this.site = site;
    }

    public void activeItem() {
        this.status = ItemStatus.ACTIVE;
    }

    public void inactiveItem() {
        this.status = ItemStatus.INACTIVE;
    }

    public void addAttribute(final Attribute attribute) {
        attributes.add(attribute);
    }

    private static void validateItem(final String categoryId, final String title) {
        validateCategoryId(categoryId);
        validateTitle(title);
    }

    private static void validateCategoryId(final String categoryId) {
        if (StringUtils.isBlank(categoryId)) {
            throw new InvalidCategoryException();
        }
    }

    private static void validateTitle(final String title) {
        if (StringUtils.isBlank(title)) {
            throw new InvalidTitleException();
        }
    }
}