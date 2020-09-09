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

@RestController
@RequestMapping("/api/top")
public class HomeController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private TagService tagService;

    @GetMapping("/collections/{amount}")
    public List<Collection> getTopCollectionsBySize(@PathVariable int amount) {
        return collectionService.sortByItemsNumber(amount);
    }

    @GetMapping("/items/{amount}")
    private List<Item> getLastItems(@PathVariable int amount) {
        return itemService.compareByDate(amount);
    }

    @GetMapping("/tags")
    private List<Tag> getTags() {
        return tagService.findAll();
    }

    @GetMapping("/tags/{id}")
    private Tag getTag(@PathVariable("id") long id) {
        return tagService.getTag(id);
    }
}
