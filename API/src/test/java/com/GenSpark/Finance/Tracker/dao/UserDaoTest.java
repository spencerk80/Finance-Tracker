package com.GenSpark.Finance.Tracker.dao;

import com.GenSpark.Finance.Tracker.entity.User;
import com.GenSpark.Finance.Tracker.enums.UserRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    private static User testUser;

    @BeforeAll
    static void createUser() {
        User user = newUser();

        testUser = user;
    }

    private static User newUser() {
        return new User(
                "testPass123", "Test", "User",
                "test.user@email.com", UserRole.USER, true
        );
    }

    @Test
    public void saveUserTest() {
        userDao.save(testUser);

        //ID is set when saved to the db
        assertTrue(testUser.getUserID() > 0);
    }

    //This test requires pre-existing data in the db
    @Test
    public void getUserTest() {
        final String email = "b.smith@email.com";
        Optional<User> retrievedCatOpt = userDao.findUserByEmail(email);

        assertTrue(retrievedCatOpt.isPresent());
        assertEquals(email, retrievedCatOpt.get().getEmail());
    }

    @Test
    public void updateUserTest() {
        Optional<User> retrievedUser;

        userDao.save(testUser);

        testUser.setEmail("bhf.ufesi@email.com");
        userDao.save(testUser);

        retrievedUser = userDao.findUserByEmail(testUser.getEmail());

        assertTrue(retrievedUser.isPresent());
        assertEquals("bhf.ufesi@email.com", retrievedUser.get().getEmail());
    }

    @Test
    public void deleteUserTest() {
        Optional<User> retrievedUser;
        User user = newUser();

        userDao.save(user);
        userDao.deleteById(user.getUserID());
        retrievedUser = userDao.findById(user.getUserID());

        assertTrue(retrievedUser.isEmpty());
    }
}
