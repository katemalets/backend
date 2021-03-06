package com.project.demo.service;

import com.project.demo.entity.Collection;
import com.project.demo.entity.Item;
import com.project.demo.entity.Tag;
import com.project.demo.entity.User;
import com.project.demo.repository.CollectionRepository;
import com.project.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private UserRepository userRepository;

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
    @Override
    public List<Collection> sortByItemsNumber(int amount) {
        List<Collection> collections = collectionRepository.findAll();
        for (Collection collection : collections) {
            Set<Item> items = collection.getItems();
            collection.setItemsNumber(items.size());
        }
        Comparator<Collection> compareByItems =
                (o1, o2) -> (int) (o2.getItemsNumber() - o1.getItemsNumber());
        collections.sort(compareByItems);
        return collections.stream().limit(amount).collect(Collectors.toList());
    }

    @Override
    public Collection addCollection(long collectionId, long userId) {
        Collection oldCollection = collectionRepository.findById(collectionId).get();
        Collection newCollection = duplicateCollection(oldCollection, userId);
        Set<Item> newItems = addItemsToCollection(oldCollection.getItems(), newCollection);
        newCollection.setItems(newItems);
        collectionRepository.save(newCollection);
        return newCollection;
    }

    private Collection duplicateCollection(Collection oldCollection, long userId){
        User user = userRepository.findById(userId).get();
        Collection newCollection = new Collection();
        newCollection.setId(0);
        newCollection.setName(oldCollection.getName());
        newCollection.setTopic(oldCollection.getTopic());
        newCollection.setImageURL(oldCollection.getImageURL());
        newCollection.setDescription(oldCollection.getDescription());
        newCollection.setUser(user);
        newCollection.setItemsNumber(oldCollection.getItemsNumber());
        return newCollection;
    }

    private Set<Item> addItemsToCollection(Set<Item> oldItems, Collection newCollection){
        Set<Item> newItems = new HashSet<>();
        for (Item oldItem : oldItems) {
            Item newItem = new Item();
            newItem.setId(0);
            newItem.setName(oldItem.getName());
            newItem.setDate(oldItem.getDate());
            newItem.setDescription(oldItem.getDescription());
            newItem.setImageURL(oldItem.getImageURL());
            newItem.setCollection(newCollection);
            addTagsToItem(oldItem.getTags(), newItem, newItems);
        }
        return newItems;
    }

    private void addTagsToItem(Set<Tag> oldTags, Item newItem, Set<Item> newItems){
        Set<Tag> newTags = new HashSet<>();
        newTags.addAll(oldTags);
        newItem.setTags(newTags);
        newItems.add(newItem);
    }

    @Override
    public Collection updateCollection(long collectionId, Collection collectionDetails) {
        Collection collection = getCollection(collectionId);
        collection.setName(collectionDetails.getName());
        collection.setDescription(collectionDetails.getDescription());
        collection.setImageURL(collectionDetails.getImageURL());
        collectionRepository.save(collection);
        return collection;
    }
}
