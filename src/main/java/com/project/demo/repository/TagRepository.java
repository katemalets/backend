package com.project.demo.repository;

import com.project.demo.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByNameContaining(@RequestParam("name") String name);
}
