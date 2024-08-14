package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Model.Post;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CategoryRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void add(Post post) throws ApiException {
        User u = userRepository.getUserById(post.getUserId());
        Category c = categoryRepository.getCategoryById(post.getCategoryId());
        if (u == null || c == null) {
            throw new ApiException("user not found");
        }
        if (c == null){
            throw new ApiException("category not found");
        }
        postRepository.save(post);
    }

    public void update(int id, Post post) throws ApiException {
        Post p = postRepository.findPostById(id);
        if (p == null){
            throw new ApiException("post not found");
        }
        p.setUserId(post.getUserId());
        p.setCategoryId(post.getCategoryId());
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        postRepository.save(p);
    }

    public void delete(int id) throws ApiException {
        Post p = postRepository.findPostById(id);
        if (p == null){
            throw new ApiException("post not found");
        }
        postRepository.delete(p);
    }

    public List<Post> getPostsByUserId(int userId) throws ApiException {
        User u = userRepository.getUserById(userId);
        if (u == null){
            throw new ApiException("user not found");
        }
        return postRepository.findPostByUserId(userId);
    }
    public List<Post> getPostsByCategoryId(int categoryId) throws ApiException {
        Category c = categoryRepository.getCategoryById(categoryId);
        if (c == null){
            throw new ApiException("category not found");
        }
        return postRepository.findPostByCategoryId(categoryId);
    }

    public Post getPostById(int id) throws ApiException {
        Post p = postRepository.findPostById(id);
        if (p == null){
            throw new ApiException("post not found");
        }
        return p;
    }

    public List<Post> getPostsByTitle(String title) throws ApiException {
        List<Post> p = postRepository.getByTitle(title);
        if (p == null){
            throw new ApiException("post not found");
        }
        return p;
    }
}
