package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.User;

import javax.mail.MessagingException;
import java.sql.SQLIntegrityConstraintViolationException;

public interface UserService {

    User getUserByID(int userID);
    User getUserByEmail(String email);
    String saveUser(User user) throws MessagingException;
    String updateUser(User user);
    String deleteUserByID(int userID);
    void sendRegConfEmail(User user) throws MessagingException;
}
