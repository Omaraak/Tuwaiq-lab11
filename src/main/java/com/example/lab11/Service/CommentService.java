package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Comment;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CommentRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public void add(Comment comment) throws ApiException {
        User u = userRepository.getUserById(comment.getUserId());
        Post p = postRepository.findPostById(comment.getPostId());
        if (u == null) {
            throw new ApiException("user not found");
        }
        if (p == null){
            throw new ApiException("post not found");
        }
        commentRepository.save(comment);
    }

    public void update(int id, Comment comment) throws ApiException {
        Comment c = commentRepository.findCommentById(id);
        if (c == null){
            throw new ApiException("comment not found");
        }
        c.setUserId(comment.getUserId());
        c.setPostId(comment.getPostId());
        c.setContent(comment.getContent());
        commentRepository.save(c);
    }

    public void delete(int id) throws ApiException {
        Comment c = commentRepository.findCommentById(id);
        if (c == null){
            throw new ApiException("comment not found");
        }
        commentRepository.delete(c);
    }

    public List<Comment> getCommentsByPostId(int postId) throws ApiException {
        Post p = postRepository.findPostById(postId);
        if (p == null){
            throw new ApiException("post not found");
        }
        return commentRepository.findCommentByPostId(postId);
    }

    public List<Comment> getCommentsByUserId(int userId) throws ApiException {
        User user = userRepository.getUserById(userId);
        if (user == null){
            throw new ApiException("user not found");
        }
        return commentRepository.findCommentByUserId(userId);
    }
}
