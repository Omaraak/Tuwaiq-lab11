package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public void update(int id, User user) throws ApiException {
        User u = userRepository.getUserById(id);
        if (u == null) {
            throw new ApiException("user not found");
        }
        u.setUserName(user.getUserName());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        userRepository.save(u);
    }

    public void delete(int id) throws ApiException {
        User u = userRepository.getUserById(id);
        if (u == null) {
            throw new ApiException("user not found");
        }
        userRepository.delete(u);
    }

    public User getUserById(int id) throws ApiException {
        User u = userRepository.getUserById(id);
        if (u == null) {
            throw new ApiException("user not found");
        }
        return u;
    }

    public User getUserByUserNameAndPassword(String userName, String password) throws ApiException {
        User u = userRepository.getUserByUserNameAndPassword(userName, password);
        if (u == null) {
            throw new ApiException("user not found");
        }
        return u;
    }
}
