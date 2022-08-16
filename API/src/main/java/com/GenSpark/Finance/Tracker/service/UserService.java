package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.User;

public interface UserService {
    User saveUser(User user);
    User getUserByID(int userID);
    User updateUser(User user);
    User deleteUserByID(int userID);
}
