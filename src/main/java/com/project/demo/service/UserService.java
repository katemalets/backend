package com.project.demo.service;

import com.project.demo.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void deleteUser(long id);

    void save(User user);

    User getUser(long id);

    User blockUser(long id);

    User unblockUser(long id);

    User makeAdmin(long id);
}
