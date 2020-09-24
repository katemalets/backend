package com.project.demo.controller;

import com.project.demo.entity.Item;
import com.project.demo.entity.Tag;
import com.project.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @DeleteMapping(path = "/{tagId}/items/{itemId}")
    private void deleteTag(@PathVariable("tagId") long tagId,
                           @PathVariable("itemId") long itemId) {
        tagService.deleteTag(tagId, itemId);
    }

    @PostMapping("/items/{itemId}")
    private Tag addTag(@RequestBody Tag tag, @PathVariable("itemId") long itemId) {
        return tagService.addTag(tag, itemId);
    }

    @GetMapping
    private List<Tag> getTags() {
        return tagService.findAll();
    }

    @GetMapping("/{id}")
    private Tag getTag(@PathVariable("id") long id) {
        return tagService.getTag(id);
    }

    @GetMapping("/top/items/{id}")
    private List<Item> showItemsByTag(@PathVariable("id") long id) {
        return tagService.findItemsByTagId(id);
    }
}
