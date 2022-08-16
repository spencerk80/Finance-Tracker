package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.User;

public interface UserService {
    void saveUser(User user);
    User getUserByID(int userID);
    void updateUser(User user);
    void deleteUserByID(int userID);
}
