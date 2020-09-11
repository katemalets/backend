package com.project.demo.controller;

import com.project.demo.entity.Collection;
import com.project.demo.entity.Item;
import com.project.demo.service.CollectionService;
import com.project.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    CollectionService collectionService;

    @GetMapping()
    public List<Item> getItems(){
        return itemService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Item showItem(@PathVariable("id") long id){
        return itemService.getItem(id);
    }

    @PutMapping(path = {"/{id}"})
    public Item update(@PathVariable("id") long id, @RequestBody Item itemDetails){
        Item item = itemService.getItem(id);
        item.setName(itemDetails.getName());
        item.setDescription(itemDetails.getDescription());
        item.setImageURL(itemDetails.getImageURL());
        itemService.save(item);
        return item;
    }

    @GetMapping(path = {"/update/{id}"})
    public Item updateItem(@PathVariable("id") long id){
        return itemService.getItem(id);
    }

    @GetMapping("/search/{name}")
    public List<Item> showSearchedItems(@PathVariable("name") String name){
        return itemService.findByNameContaining(name);
    }

    @DeleteMapping(path = {"/{id}"})
    public void deleteItem(@PathVariable("id") long id) {
        itemService.deleteItem(id);
    }

    @PostMapping(path = {"/collections/{id}"})
    public Item addItem(@RequestBody Item item, @PathVariable("id") Long collectionId){
        Collection collection = collectionService.getCollection(collectionId);
        item.setId((long) 0);
        item.setCollection(collection);
        item.setDate(System.currentTimeMillis());
        item.setLikesNumber(0);
        itemService.save(item);
        return item;
    }

    @PutMapping(path = {"/{userId}/like/{itemId}"})
    public Item likeItem(@PathVariable("itemId") long itemId,@PathVariable("userId") long userId){
        return this.itemService.likeItem(itemId,userId);
    }

    @PutMapping(path = {"/{userId}/dislike/{itemId}"})
    public Item dislikeItem(@PathVariable("itemId") long itemId,@PathVariable("userId") long userId){
        return this.itemService.dislikeItem(itemId,userId);
    }
}
