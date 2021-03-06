package com.project.demo.service;

import com.project.demo.entity.Item;
import com.project.demo.entity.Tag;
import com.project.demo.repository.ItemRepository;
import com.project.demo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class  TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> sortByItemsNumber() {
        List<Tag> tags = tagRepository.findAll();
        Comparator<Tag> compareByItemsNumber = ((o1, o2) -> (int) (o2.getItemsNumber() - o1.getItemsNumber()));
        tags.sort(compareByItemsNumber);
        return tags;
    }

    @Override
    public Tag getTag(long id) {
        return tagRepository.findById(id).get();
    }

    @Override
    public void deleteTag(long tagId, long itemId) {
        Tag deletedTag = tagRepository.findById(tagId).get();
        Item item = itemRepository.findById(itemId).get();
        Set<Tag> tags = item.getTags();
        tags.remove(deletedTag);
        if(deletedTag.getItemsNumber() == 1){
            tagRepository.delete(deletedTag);
        }
        item.setTags(tags);
        itemRepository.save(item);
    }

    @Override
    public Tag addTag(Tag tagDetails, long itemId) {

        List<Tag> tags = tagRepository.findAll();
        Item item = itemRepository.findById(itemId).get();
        Set<Tag> itemTags = item.getTags();
        boolean flagForAddingTag = true;
        for (Tag currentTag : tags) {
            if (currentTag.getName().equals(tagDetails.getName())) {
                itemTags.add(currentTag);
                flagForAddingTag = false;
                break;
            }
        }
        if (flagForAddingTag) {
           addNewTag(tagDetails, itemTags);
        }
        item.setTags(itemTags);
        itemRepository.save(item);
        return null;
    }

    private void addNewTag(Tag tagDetails, Set<Tag> itemTags ){
        Tag newTag = new Tag();
        newTag.setId(0);
        newTag.setName(tagDetails.getName());
        itemTags.add(newTag);
        tagRepository.save(newTag);
    }

    @Override
    public List<Item> findItemsByTagId(long id) {
        List<Item> items = itemRepository.findAll();
        List<Item> newItems = new ArrayList<>();
        Tag tag = tagRepository.findById(id).get();
        for (Item item : items) {
            Set<Tag> itemTags = item.getTags();
            for (Tag currentTag : itemTags) {
                if (currentTag.getName().equals(tag.getName())) {
                    newItems.add(item);
                }
            }
        }
        return newItems;
    }
}
