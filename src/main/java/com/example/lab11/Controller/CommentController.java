package com.example.lab11.Controller;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Comment;
import com.example.lab11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bs/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Comment>> getAll(){
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@Valid @RequestBody Comment comment, Errors errors) throws ApiException {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.add(comment);
        return ResponseEntity.status(201).body("Comment added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@Valid @RequestBody Comment comment, @PathVariable int id, Errors errors) throws ApiException {
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.update(id, comment);
        return ResponseEntity.status(200).body("Comment updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable int id) throws ApiException {
        commentService.delete(id);
        return ResponseEntity.status(200).body("Comment deleted");
    }

    @GetMapping("/getCommentsByPostId/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable int postId) throws ApiException {
        return ResponseEntity.status(200).body(commentService.getCommentsByPostId(postId));
    }

    @GetMapping("/getCommentsByUserId/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable int userId) throws ApiException {
        return ResponseEntity.status(200).body(commentService.getCommentsByUserId(userId));
    }
}
