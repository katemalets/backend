package com.project.demo.controller;

import com.project.demo.entity.Collection;
import com.project.demo.entity.User;
import com.project.demo.service.CollectionService;
import com.project.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collections")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<Collection> showCollections(){
        return collectionService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public Collection showCollection(@PathVariable("id") long id){
        return collectionService.getCollection(id);
    }

    @PutMapping(path = {"/{id}"})
    public Collection update(@PathVariable("id") long id, @RequestBody Collection collectionDetails){
        Collection collection = collectionService.getCollection(id);
        collection.setName(collectionDetails.getName());
        collection.setDescription(collectionDetails.getDescription());
        collection.setImageURL(collectionDetails.getImageURL());
        collectionService.save(collection);

        return collection;
    }

    @DeleteMapping(path = {"/{id}"})
    public void deleteCollection(@PathVariable("id") long id){
        collectionService.deleteCollection(id);
    }

    @GetMapping(path = {"/update/{id}"})
    public Collection updateCollection(@PathVariable("id") long id){
        return collectionService.getCollection(id);
    }

    @GetMapping(path = {"/list/{id}"})
    public Collection showItems(@PathVariable("id") long id){
        return collectionService.getCollection(id);
    }

    @GetMapping(path = {"/{userId}/{collectionId}"})
    public Collection addCollection(@PathVariable("userId") long userId,
                                    @PathVariable("collectionId") long collectionId){
        return collectionService.addCollection(userId, collectionId);
    }
}
