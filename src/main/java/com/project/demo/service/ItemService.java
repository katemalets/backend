package com.project.demo.service;

import com.project.demo.entity.Item;

import java.util.List;

public interface ItemService {

    Item getItem(long id);

    List<Item> findAll();
}
