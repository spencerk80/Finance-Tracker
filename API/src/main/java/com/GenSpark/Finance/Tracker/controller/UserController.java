package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.User;
import com.GenSpark.Finance.Tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userID}")
    public ResponseEntity<User> getUser(@PathVariable String userID) {
        return ResponseEntity.ok().body(this.userService.getUserByID(Integer.parseInt(userID)));
    }

    @PostMapping("/users")
    public ResponseEntity<String> saveDeposit(@RequestBody User user) {
        return ResponseEntity.ok().body(this.userService.saveUser(user));
    }

    @PutMapping("/users")
    public ResponseEntity<String> updateDeposit(@RequestBody User user) {
        return ResponseEntity.ok().body(this.userService.updateUser(user));
    }

    @DeleteMapping("/users/{userID}")
    public ResponseEntity<String> deleteCategory(@PathVariable String userID) {
        return ResponseEntity.ok().body(this.userService.deleteUserByID(Integer.parseInt(userID)));
    }
}
