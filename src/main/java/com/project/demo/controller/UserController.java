package com.project.demo.controller;

import com.project.demo.entity.User;
import com.project.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = {"/{id}"})
    public User showUser(@PathVariable("id") long id){
       return userService.getUser(id);
    }

    @DeleteMapping(path ={"/{id}"})
    public void deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
    }

    @PutMapping(path = {"/block/{id}"})
    public User blockUser(@PathVariable("id") long  id){
        return userService.blockUser(id);
    }

    @PutMapping(path = {"/unblock/{id}"})
    public User unblockUser(@PathVariable("id") long  id){
        return userService.unblockUser(id);
    }

    @PutMapping(path = {"/makeAdmin/{id}"})
    public User makeAdmin(@PathVariable("id") long  id){
        return userService.makeAdmin(id);
    }

}
