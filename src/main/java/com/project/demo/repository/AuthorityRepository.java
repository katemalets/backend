package com.project.demo.repository;

import com.project.demo.entity.Authority;
import com.project.demo.entity.AuthorityEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Optional<Authority> findByAuthority(AuthorityEnum authority);
}
