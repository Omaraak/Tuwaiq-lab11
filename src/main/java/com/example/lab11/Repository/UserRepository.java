package com.example.lab11.Repository;

import com.example.lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.id=?1")
    User getUserById(int id);

    @Query("select u from User u where u.userName=?1 and u.password=?2")
    User getUserByUserNameAndPassword(String username, String password);
}
