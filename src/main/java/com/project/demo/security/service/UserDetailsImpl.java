package com.project.demo.security.service;

import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.demo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;


    private List<com.project.demo.entity.Collection> collections;

    private List<com.project.demo.entity.Collection> images;

    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    private String password;

    public UserDetailsImpl(Long id, String username, String email, List<com.project.demo.entity.Collection> collections, String password,
                           Collection<? extends GrantedAuthority> authorities,
                           List<com.project.demo.entity.Collection> images) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.collections = collections;
        this.images = images;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getAuthorities().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority().name()))
                .collect(Collectors.toList());
        List<com.project.demo.entity.Collection> collections = user.getCollections().stream()
                .map(collection -> new com.project.demo.entity.Collection(collection.getName(),collection.getImageURL()))
                .collect(Collectors.toList());

        List<com.project.demo.entity.Collection> images = user.getCollections().stream()
                .map(collection -> new com.project.demo.entity.Collection(collection.getImageURL(),collection.getName()))
                .collect(Collectors.toList());

        System.out.println(collections);
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                collections,
                user.getPassword(),
                authorities,images);
    }

    public List<com.project.demo.entity.Collection> getImages() {
        return images;
    }

    public List<com.project.demo.entity.Collection> getCollections() {
        return collections;
    }

    public void setCollections(List<com.project.demo.entity.Collection> collections) {
        this.collections = collections;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}

