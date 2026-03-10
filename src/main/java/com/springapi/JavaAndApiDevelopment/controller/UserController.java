package com.springapi.JavaAndApiDevelopment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.springapi.JavaAndApiDevelopment.dto.UserRequest;
import com.springapi.JavaAndApiDevelopment.dto.UserResponse;
import com.springapi.JavaAndApiDevelopment.service.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    // GET ALL USERS
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userServiceImpl.fetchAllUsers(), HttpStatus.OK);
    }

    // GET USER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        return userServiceImpl.fetchUser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE USER
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userServiceImpl.addUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
    }

    // UPDATE USER
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestBody UserRequest updateUserRequest) {

        boolean updated = userServiceImpl.updateUser(id, updateUserRequest);

        if (updated) {
            return ResponseEntity.ok("User updated successfully");
        }

        return ResponseEntity.notFound().build();
    }
}
