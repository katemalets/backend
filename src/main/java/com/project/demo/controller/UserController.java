package com.project.demo.controller;

import com.project.demo.entity.User;
import com.project.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class UserController {

    //toDo social networks entering
    //toDo search full text
    //toDo drag n drop
    //toDo markdown format
    //toDo cloud tags
    //toDo websockets comment

    @Autowired
    private UserService userService;

    @GetMapping(path = {"/{id}"})
    public User showUser(@PathVariable("id") long id){
        return userService.getUser(id);
    }


}
