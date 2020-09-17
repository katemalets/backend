package com.project.demo.service;

import com.project.demo.entity.Collection;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CollectionService {

    Collection getCollection(long id);

    List<Collection> findAll();

    void save(Collection collection);

    void deleteCollection(long id);

    List<Collection> sortByItemsNumber(int amount);

    Collection addCollection(long collectionId, long userId);

    Collection updateCollection(long id, Collection collectionDetails);
}
