package com.project.demo.controller;

import com.project.demo.entity.Collection;
import com.project.demo.entity.Item;
import com.project.demo.entity.Tag;
import com.project.demo.service.CollectionService;
import com.project.demo.service.ItemService;
import com.project.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/top/")
public class HomeController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private TagService tagService;
    //уточнить название!!!

    @GetMapping("collections")
    public List<Collection> getCollectionsWithMoreItems(){
       List<Collection> collections = collectionService.compareByItemsNumber();
        return collections.stream().limit(3).collect(Collectors.toList());
    }

    @GetMapping("items")
    private List<Item> getLastItems(){
        List<Item> items = itemService.compareByDate();
        return items.stream().limit(6).collect(Collectors.toList());
    }

    @GetMapping("tags")
    private List<Tag> getTags(){
        return tagService.findAll();
    }

    @GetMapping("/tags/{id}")
    private Tag getTag(@PathVariable("id") long id){
        return tagService.getTag(id);
    }
}
