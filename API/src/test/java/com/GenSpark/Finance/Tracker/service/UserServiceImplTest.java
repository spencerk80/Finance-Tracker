package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.UserDao;
import com.GenSpark.Finance.Tracker.entity.User;
import com.GenSpark.Finance.Tracker.enums.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @Test
    void saveUser() {
        userService.saveUser(new User("pass123", "John", "Doe", "j.doe@gmail.com", UserRole.USER, true));
        verify(userDao, times(1)).save(new User("pass123", "John", "Doe", "j.doe@gmail.com", UserRole.USER, true));
    }

    @Test
    void getUserByID() {
        int userID = 0;
        final User user = new User("pass123", "John", "Doe", "j.doe@gmail.com", UserRole.USER, true);
        given(userDao.findById(userID)).willReturn(Optional.of(user));
        User expected = userService.getUserByID(userID);
        System.out.println(expected);
        assertThat(expected).isNotNull();
        assertEquals(user.getEmail(), expected.getEmail());
    }

    @Test
    void updateUser() {
        User updatedUser = new User("pass123", "John", "Doe", "j.doe@gmail.com", UserRole.USER, true);
        given(userDao.save(updatedUser)).willReturn(updatedUser);
        userService.updateUser(updatedUser);
        assertThat(updatedUser).isNotNull();
        verify(userDao).save(any(User.class));
    }

    @Test
    void deleteUserByID() {
        final int userID = 0;
        userService.deleteUserByID(userID);
        verify(userDao, times(1)).deleteById(userID);
    }
}