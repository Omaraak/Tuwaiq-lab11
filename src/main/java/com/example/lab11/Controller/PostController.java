package com.example.lab11.Controller;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Post;
import com.example.lab11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bs/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Post>> getAll(){
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post, Errors errors) throws ApiException {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.add(post);
        return ResponseEntity.status(201).body("Post added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@Valid @RequestBody Post post, @PathVariable int id, Errors errors) throws ApiException {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.update(id, post);
        return ResponseEntity.status(200).body("Post updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable int id) throws ApiException {
        postService.delete(id);
        return ResponseEntity.status(200).body("Post deleted");
    }

    @GetMapping("/getPostByUserId/{userId}")
    public ResponseEntity getPostByUserId(@PathVariable int userId) throws ApiException {
        return ResponseEntity.status(200).body(postService.getPostsByUserId(userId));
    }

    @GetMapping("/getPostByCategoryId/{categoryId}")
    public ResponseEntity getPostByCategoryId(@PathVariable int categoryId) throws ApiException {
        return ResponseEntity.status(200).body(postService.getPostsByCategoryId(categoryId));
    }

    @GetMapping("/getPostByTitle/{title}")
    public ResponseEntity getPostByTitle(@PathVariable String title) throws ApiException {
        return ResponseEntity.status(200).body(postService.getPostsByTitle(title));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getPostById(@PathVariable int id) throws ApiException {
        return ResponseEntity.status(200).body(postService.getPostById(id));
    }
}
