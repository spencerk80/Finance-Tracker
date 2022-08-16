package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.User;
import com.GenSpark.Finance.Tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{userID}")
    public User getUser(@PathVariable String userID) {
        return this.userService.getUserByID(Integer.parseInt(userID));
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        return this.userService.saveUser(user);
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        return this.userService.updateUser(user);
    }

    @DeleteMapping("/users/{userID}")
    public User deleteUser(@PathVariable String userID) {
        return this.userService.deleteUserByID(Integer.parseInt(userID));
    }
}
