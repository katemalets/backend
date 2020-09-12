package com.project.demo.service;

import com.project.demo.entity.Item;
import com.project.demo.entity.User;
import com.project.demo.repository.ItemRepository;
import com.project.demo.repository.UserRepository;
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

    @Override
    public Item getItem(long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> findByNameContaining(String name){
        return itemRepository.findByNameContaining(name);
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
        List<Item> items = findAll();
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
        if(!item.getUsersWhoLiked().contains(user)){
            System.out.println(user);
            item.setLikesNumber(item.getLikesNumber() + 1);
            item.getUsersWhoLiked().add(user);
            item.setUserLiked(true);
        }
        itemRepository.save(item);
        return item;
    }

    @Override
    public Item dislikeItem(long itemId, long userId) {
        Item item = itemRepository.findById(itemId).get();
        User user = userRepository.findById(userId).get();
        System.out.println("did user like item? - " + item.getUsersWhoLiked().contains(user));
        if(item.getUsersWhoLiked().contains(user)){
            item.setLikesNumber(item.getLikesNumber() - 1);
            item.getUsersWhoLiked().remove(user);
            item.setUserLiked(false);
        }
        itemRepository.save(item);
        return item;
    }
}
