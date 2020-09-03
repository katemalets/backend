package com.project.demo.controller;

import com.project.demo.entity.Item;
import com.project.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping(path = {"/{id}"})
    public Item showItem(@PathVariable("id") long id){
        return itemService.getItem(id);
    }

    @GetMapping()
    public List<Item> showItems(){
        return itemService.findAll();
    }

    @GetMapping("/search/findByNameContaining/{name}")
    public Page<Item> showSearchedItems(@PathVariable("name") String name, Pageable pageable){
        return itemService.findByNameContaining(name, pageable);
    }
}
