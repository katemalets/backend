package com.project.demo.service;

import com.project.demo.entity.Item;
import com.project.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item getItem(long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
