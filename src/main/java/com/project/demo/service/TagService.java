package com.project.demo.service;

import com.project.demo.entity.Tag;

import java.util.List;

public interface TagService {

    List<Tag> findAll();

    Tag getTag(long id);

    void deleteTag(long tagId, long itemId);

    Tag addTag(Tag tag, long itemId);
}
