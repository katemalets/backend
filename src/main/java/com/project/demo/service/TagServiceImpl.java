package com.project.demo.service;

import com.project.demo.entity.Item;
import com.project.demo.entity.Tag;
import com.project.demo.repository.ItemRepository;
import com.project.demo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
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
        item.setTags(tags);
        itemRepository.save(item);
    }
}
