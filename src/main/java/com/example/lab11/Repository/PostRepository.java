package com.example.lab11.Repository;

import com.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findPostById(int id);
    List<Post> findPostByUserId(int userId);
    List<Post> findPostByCategoryId(int categoryId);
    @Query("select p from Post p where p.title=?1")
    List<Post> getByTitle(String title);
}
