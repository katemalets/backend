package com.project.demo.service;

import com.project.demo.entity.Collection;
import com.project.demo.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Override
    public Collection getCollection(long id) {
        return collectionRepository.findById(id).get();
    }

    @Override
    public List<Collection> findAll() {
        return collectionRepository.findAll();
    }
}
