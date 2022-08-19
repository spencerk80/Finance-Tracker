package com.GenSpark.Finance.Tracker.util;

import com.GenSpark.Finance.Tracker.dao.UserDao;
import com.GenSpark.Finance.Tracker.security.UserDetailsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JwtTest {

    private UserDao userDao;

    private UserDetailsService userDetailsService;
    private UserDetails userDetails;

    private final String    userEmail = "b.smith@email.com",
                            userRole = "[USER]";
    private String jwt;

    @Autowired
    public JwtTest(UserDao dao) {
        userDao = dao;
        userDetailsService = new UserDetailsService(dao);
    }

    @BeforeAll
    public void mkUser() {
        userDetails = userDetailsService.loadUserByUsername(userEmail);

        if(userDetails == null) throw new UsernameNotFoundException("Database data not pre-loaded");
        jwt = JWT.createJWT(userDetails);
    }

    @Test
    public void createJwt() {
        //Matches a JWT pattern
        assertTrue(jwt.matches("^[\\da-zA-z_-]+.\\.[\\da-zA-z_-]+\\.[\\da-zA-z_-]+$"));
    }

    @Test
    public void getUserEmail() {
        assertEquals(userEmail, JWT.getUserEmail(jwt));
    }

    @Test
    public void getUserRole() {
        assertEquals(userRole, JWT.getUserRole(jwt));
    }

    @Test
    public void getExp() {
        LocalDateTime exp           = JWT.getJwtExpiration(jwt);
        LocalDateTime oneDayBefore  = LocalDateTime.now().minusDays(1);
        LocalDateTime oneDayAfter   = LocalDateTime.now().plusDays(2);

        assertTrue(exp.isAfter(oneDayBefore) && exp.isBefore(oneDayAfter));
    }

    @Test
    public void validateJWT() {
        char charToMod = jwt.charAt(40);
        //Replace the char at index 40 with the letter 'a', unless it happens to be that already, then 'b'
        String modifiedJwt = jwt.substring(0, 40) + (charToMod == 'a' ? 'b' : 'a') + jwt.substring(41);

        assertTrue(JWT.validateJwt(jwt, userDetails));
        assertFalse(JWT.validateJwt(modifiedJwt, userDetails));
    }

    //z is prefixed to make the test run last
    @Test
    public void z_blacklistJwt() {
        String newJwt;

        assertTrue(JWT.validateJwt(jwt, userDetails));
        JWT.blacklistJwt(jwt);
        assertFalse(JWT.validateJwt(jwt, userDetails));

        newJwt = JWT.createJWT(userDetails);
        assertTrue(JWT.validateJwt(newJwt, userDetails));
    }
}
