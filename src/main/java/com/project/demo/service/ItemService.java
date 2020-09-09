package com.project.demo.service;

import com.project.demo.entity.Item;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ItemService {

    Item getItem(long id);

    List<Item> findAll();

    List<Item> findByNameContaining(@RequestParam("name") String name);

    void deleteItem(long id);

    void save(Item item);

    List<Item> compareByDate(int amount);
}
