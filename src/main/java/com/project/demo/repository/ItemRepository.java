package com.project.demo.repository;

import com.project.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNameContaining(@RequestParam("name") String name);

    List<Item> findByDescriptionContaining(@RequestParam("description") String description);
}
