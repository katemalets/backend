package com.project.demo.service;

import com.project.demo.entity.Item;
import com.project.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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

    @Override
    public List<Item> findByNameContaining(String name){
        return itemRepository.findByNameContaining(name);
    }

    @Override
    public void deleteItem(long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> compareByDate() {
        List<Item> items = findAll();
        Comparator<Item> compareByDate = ((o1, o2) -> (int) (o2.getDate() - o1.getDate()));
        items.sort(compareByDate);
        return items;
    }
}
