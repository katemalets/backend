package com.project.demo.service;

import com.project.demo.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ItemService {

    Item getItem(long id);

    List<Item> findAll();

    Page<Item> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
}
