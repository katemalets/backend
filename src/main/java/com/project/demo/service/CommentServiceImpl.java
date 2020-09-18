package com.project.demo.service;

import com.project.demo.entity.Comment;
import com.project.demo.entity.Item;
import com.project.demo.entity.User;
import com.project.demo.repository.CommentRepository;
import com.project.demo.repository.ItemRepository;
import com.project.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment, long itemId, long userId) {
        User user = userRepository.findById(userId).get();
        Item item = itemRepository.findById(itemId).get();
        comment.setId(0);
        comment.setUser(user);
        comment.setItem(item);
//        List<Comment> comments = item.getComments();
//        comments.add(comment);
//        item.setComments(comments);
//        itemRepository.save(item);
        commentRepository.save(comment);
        return comment;
    }
}
