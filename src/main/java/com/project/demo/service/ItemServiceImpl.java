package com.project.demo.service;

import com.project.demo.entity.*;
import com.project.demo.entity.Collection;
import com.project.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagService tagService;

    @Override
    public Item getItem(long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> findItems(String name) {
        List<Item> searchedItems = new ArrayList<>();
        List<Item> itemsByName = itemRepository.findByNameContaining(name);
        List<Item> itemsByDescription = itemRepository.findByDescriptionContaining(name);
        List<Tag> tags = tagRepository.findByNameContaining(name);
        List<Item> itemsByTag = new ArrayList<>();
        for (Tag currentTag : tags) {
            itemsByTag = tagService.findItemsByTagId(currentTag.getId());
        }
        searchedItems.addAll(itemsByName);
        searchedItems.addAll(itemsByDescription);
        searchedItems.addAll(itemsByTag);
        return searchedItems;
    }

    @Override
    public Item updateItem(long id, Item itemDetails) {
        Item item = getItem(id);
        item.setName(itemDetails.getName());
        item.setDescription(itemDetails.getDescription());
        item.setImageURL(itemDetails.getImageURL());

        List<Tag> tags = tagRepository.findAll();
        Set<Tag> itemTags = itemDetails.getTags();
        System.out.println(itemTags);
        Set<Tag> newTags = new HashSet<>();

        for (Tag oldTag : itemTags) {
            boolean flagForAddingTag = true;
            for (Tag currentTag : tags) {
                if (currentTag.getName().equals(oldTag.getName())) {
                    System.out.println("We have this item " + oldTag.getName());
                    newTags.add(currentTag);
                    flagForAddingTag = false;
                    break;
                }
            }
            if (flagForAddingTag) {
                System.out.println("Adding new tag + " + oldTag.getName());
                Tag newTag = new Tag();
                newTag.setId(0);
                newTag.setName(oldTag.getName());
                newTags.add(newTag);
                tagRepository.save(newTag);
            }
        }
        item.setTags(newTags);
        itemRepository.save(item);
        return item;
    }

    @Override
    public Item addItem(Item item, long collectionId) {
        Collection collection = collectionRepository.findById(collectionId).get();
        item.setId(0);
        item.setCollection(collection);
        item.setDate(System.currentTimeMillis());
        item.setLikesNumber(0);
        itemRepository.save(item);
        return item;
    }

    @Override
    public void deleteItem(long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> compareByDate(int amount) {
        List<Item> items = itemRepository.findAll();
        Comparator<Item> compareByDate = ((o1, o2) -> (int) (o2.getDate() - o1.getDate()));
        items.sort(compareByDate);
        return items.stream().limit(amount).collect(Collectors.toList());
    }

    @Override
    public Item likeItem(long itemId, long userId) {
        Item item = itemRepository.findById(itemId).get();
        User user = userRepository.findById(userId).get();
        System.out.println("Users who liked: " + item.getUsersWhoLiked());
        System.out.println("did user like item? - " + item.getUsersWhoLiked().contains(user));
        if (!item.getUsersWhoLiked().contains(user)) {
            System.out.println(user);
            item.setLikesNumber(item.getLikesNumber() + 1);
            item.getUsersWhoLiked().add(user);
        }
        itemRepository.save(item);
        return item;
    }

    @Override
    public Item dislikeItem(long itemId, long userId) {
        Item item = itemRepository.findById(itemId).get();
        User user = userRepository.findById(userId).get();
        System.out.println("did user like item? - " + item.getUsersWhoLiked().contains(user));
        if (item.getUsersWhoLiked().contains(user)) {
            item.setLikesNumber(item.getLikesNumber() - 1);
            item.getUsersWhoLiked().remove(user);
        }
        itemRepository.save(item);
        return item;
    }
}
