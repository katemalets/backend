package com.project.demo.repository;

import com.project.demo.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
}
