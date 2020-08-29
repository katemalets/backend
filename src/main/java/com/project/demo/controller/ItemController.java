package com.project.demo.controller;

import com.project.demo.entity.Item;
import com.project.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
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
}
