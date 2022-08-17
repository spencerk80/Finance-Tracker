package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.UserDao;
import com.GenSpark.Finance.Tracker.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public User getUserByID(int userID) {
        Optional<User> user = userDao.findById(userID);
        if (user.isPresent()) return user.get();
        else throw new RuntimeException("User with ID: " + userID + " not found.");
    }

    @Override
    public void updateUser(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteUserByID(int userID) {
        userDao.deleteById(userID);
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> user = userDao.findUserByEmail(email);
        if (user.isPresent()) return user.get();
        else throw new RuntimeException("User with email: " + email + " not found.");
    }
}
