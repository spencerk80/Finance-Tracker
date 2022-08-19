package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.NewUserRequest;
import com.GenSpark.Finance.Tracker.entity.User;
import com.GenSpark.Finance.Tracker.enums.UserRole;
import com.GenSpark.Finance.Tracker.service.UserService;
import com.GenSpark.Finance.Tracker.util.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
public class UserController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users/{userID}")
    public User getUser(@PathVariable String userID) {
        return this.userService.getUserByID(Integer.parseInt(userID));
    }

    @PostMapping("/users")
    public void saveUser(@RequestBody User user) {
        try {
            this.userService.saveUser(user);
        } catch(SQLIntegrityConstraintViolationException e) {

        }
    }

    @PostMapping("/users/register")
    public ResponseEntity<String> registerUser(@RequestBody NewUserRequest user) throws URISyntaxException {
        if( ! user.validate()) return ResponseEntity.badRequest().body("Invalid user data");

        User validUser = new User(
                passwordEncoder.encode(user.getPassword()), user.getFname(),
                user.getLname(), user.getEmail(), UserRole.USER, false
        );

        try {
            userService.saveUser(validUser);
        } catch(SQLIntegrityConstraintViolationException e) {
            return ResponseEntity.badRequest().body("Duplicate data");
        }

        return ResponseEntity.created(new URI("/users/register")).body("");
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User user) {
        this.userService.updateUser(user);
    }

    @DeleteMapping("/users/{userID}")
    public void deleteUser(@PathVariable String userID) {
        this.userService.deleteUserByID(Integer.parseInt(userID));
    }
}
