package com.project.demo.service;

import com.project.demo.entity.Comment;

public interface CommentService {
    Comment addComment(Comment comment, long itemId, long userId);
}
