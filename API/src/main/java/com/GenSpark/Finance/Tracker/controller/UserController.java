package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.NewUserRequest;
import com.GenSpark.Finance.Tracker.entity.User;
import com.GenSpark.Finance.Tracker.enums.UserRole;
import com.GenSpark.Finance.Tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;

@RestController
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users/{userID}")
    public ResponseEntity<User> getUser(@PathVariable String userID) {
        return ResponseEntity.ok().body(this.userService.getUserByID(Integer.parseInt(userID)));
    }

    @PostMapping("/users")
    public ResponseEntity<String> saveUser(@RequestBody User user) throws MessagingException {
        this.userService.saveUser(user);
        return ResponseEntity.ok().body("User saved");
    }

    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@RequestBody NewUserRequest user) throws URISyntaxException {
        if( ! user.validate()) return ResponseEntity.badRequest().body("Invalid user data");

        User validUser;
        validUser = new User(
                passwordEncoder.encode(user.getPassword()), user.getFname(),
                user.getLname(), user.getEmail(), UserRole.USER, false
        );

        userService.saveUser(validUser);

        return ResponseEntity.created(new URI("/users/register")).body("");
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
