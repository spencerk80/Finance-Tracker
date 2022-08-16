package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.User;
import com.GenSpark.Finance.Tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{userID}")
    public User getCategory(@PathVariable String userID) {
        return this.userService.getUserByID(Integer.parseInt(userID));
    }

    @PostMapping("/users")
    public User saveCategory(@RequestBody User user) {
        return this.userService.saveUser(user);
    }

    @PutMapping("/users")
    public User updateCategory(@RequestBody User user) {
        return this.userService.updateUser(user);
    }

    @DeleteMapping("/users/{userID}")
    public User deleteCategory(@PathVariable String userID) {
        return this.userService.deleteUserByID(Integer.parseInt(userID));
    }
}
