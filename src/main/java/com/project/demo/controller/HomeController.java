package com.project.demo.controller;

import com.project.demo.entity.Collection;
import com.project.demo.entity.Item;
import com.project.demo.service.CollectionService;
import com.project.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/top/")
public class HomeController {

    @Autowired
    private CollectionService collectionService;


    //уточнить название!!!
    @Autowired
    private ItemService itemService;
    @GetMapping("collections")
    public List<Collection> getCollectionsWithMoreItems(){
       List<Collection> collections = collectionService.compareByItemsNumber();
        return collections.stream().limit(3).collect(Collectors.toList());
    }

    @GetMapping("items")
    public List<Item> getLastItems(){
        List<Item> items = itemService.compareByDate();
        return items.stream().limit(6).collect(Collectors.toList());
    }
}
