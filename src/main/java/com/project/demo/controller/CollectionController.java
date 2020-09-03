package com.project.demo.controller;

import com.project.demo.entity.Collection;
import com.project.demo.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collections")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

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

    @GetMapping(path = {"/update/{id}"})
    public Collection updateCollection(@PathVariable("id") long id){
        return collectionService.getCollection(id);
    }
}
