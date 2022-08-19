package com.GenSpark.Finance.Tracker.util;

import com.GenSpark.Finance.Tracker.dao.UserDao;
import com.GenSpark.Finance.Tracker.security.UserDetailsService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JwtTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserDetailsService userDetailsService = new UserDetailsService(userDao);
    private UserDetails userDetails;

    @BeforeAll
    public void mkUser() {
        userDetails = userDetailsService.loadUserByUsername("b.smith@email.com");

        if(userDetails == null) throw new UsernameNotFoundException("Database data not pre-loaded");
    }

    @Test
    public void createJwt() {

    }
}
