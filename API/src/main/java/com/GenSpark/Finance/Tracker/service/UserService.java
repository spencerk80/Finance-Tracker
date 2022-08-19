package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.User;

import java.sql.SQLIntegrityConstraintViolationException;

public interface UserService {
    void saveUser(User user) throws SQLIntegrityConstraintViolationException;
    User getUserByID(int userID);
    void updateUser(User user);
    void deleteUserByID(int userID);
    User getUserByEmail(String email);
}
