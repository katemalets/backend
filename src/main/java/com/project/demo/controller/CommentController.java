package com.project.demo.controller;

import com.project.demo.entity.Comment;
import com.project.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping(path = {"/{itemId}/{userId}"})
    public Comment addComment(@RequestBody Comment comment,
                              @PathVariable("itemId") long itemId,
                              @PathVariable("userId") long userId) {
        return commentService.addComment(comment, itemId, userId);
    }
}
