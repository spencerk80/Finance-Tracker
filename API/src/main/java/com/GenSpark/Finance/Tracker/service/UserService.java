package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.User;

import java.sql.SQLIntegrityConstraintViolationException;

public interface UserService {

    User getUserByID(int userID);
    User getUserByEmail(String email);
    String saveUser(User user);
    String updateUser(User user);
    String deleteUserByID(int userID);
}
