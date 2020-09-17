package com.project.demo.service;

import com.project.demo.entity.Item;

import java.util.List;

public interface ItemService {

    Item getItem(long id);

    List<Item> findAll();

    List<Item> findItems(String name);

    void deleteItem(long id);

    void save(Item item);

    List<Item> compareByDate(int amount);

    Item likeItem(long itemId, long userId);

    Item dislikeItem(long itemId, long userId);

    Item updateItem(long id, Item itemDetails);

    Item addItem(Item item, long collectionId);
}
