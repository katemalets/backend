package com.project.demo.service;

import com.project.demo.entity.Collection;

import java.util.List;

public interface CollectionService {

    Collection getCollection(long id);

    List<Collection> findAll();

    void save(Collection collection);

    void deleteCollection(long id);

    List<Collection> compareByItemsNumber();
}
