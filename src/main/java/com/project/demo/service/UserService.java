package com.project.demo.service;

import com.project.demo.entity.User;
import com.project.demo.security.payload.request.LoginRequest;
import com.project.demo.security.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void deleteUser(long id);

    void save(User user);

    User getUser(long id);

    User blockUser(long id);

    User unblockUser(long id);

    User makeAdmin(long id);

    ResponseEntity<?> registerUser(SignupRequest signUpRequest);

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);
}
