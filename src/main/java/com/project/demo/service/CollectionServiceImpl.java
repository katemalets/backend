package com.project.demo.service;

import com.project.demo.entity.Collection;
import com.project.demo.entity.Item;
import com.project.demo.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Override
    public Collection getCollection(long id) {
        return collectionRepository.findById(id).get();
    }

    public List<Collection> findAll() {
        return collectionRepository.findAll();
    }

    @Override
    public void save(Collection collection) {
        collectionRepository.save(collection);
    }

    @Override
    public void deleteCollection(long id) {
        collectionRepository.deleteById(id);
    }

    //toDo from db(select from db(not working here)
    //toDo hibernate search(!!!findInfo!!!)
    //toDo checkboxes!(instead of enabled)
    @Override
    public List<Collection> sortByItemsNumber(int amount) {
        List<Collection> collections = findAll();
        for(Collection collection: collections){
            Set<Item> items = collection.getItems();
            collection.setItemsNumber(items.size());
        }

        Comparator<Collection> compareByItems =
                (o1, o2) -> (int) (o2.getItemsNumber() - o1.getItemsNumber());
        collections.sort(compareByItems);
        return collections.stream().limit(amount).collect(Collectors.toList());
    }
}
