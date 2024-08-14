package com.example.lab11.Controller;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.User;
import com.example.lab11.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bs/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.status(200).body(userService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.add(user);
        return ResponseEntity.status(201).body("User added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@Valid @RequestBody User user, @PathVariable int id, Errors errors) throws ApiException {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.update(id, user);
        return ResponseEntity.status(200).body("User updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id) throws ApiException {
        userService.delete(id);
        return ResponseEntity.status(200).body("User deleted");
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) throws ApiException {
        return ResponseEntity.status(200).body(userService.getUserById(id));
    }

    @GetMapping("/getUserByUserNameAndPassword/{userName}/{password}")
    public ResponseEntity<User> getUserByUserNameAndPassword(@PathVariable String userName, @PathVariable String password) throws ApiException {
        return ResponseEntity.status(200).body(userService.getUserByUserNameAndPassword(userName,password));
    }
}
