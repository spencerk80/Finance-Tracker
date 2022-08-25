package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.security.UserDetailsService;
import com.GenSpark.Finance.Tracker.util.JWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JwtBlacklistServiceTest {
    private final JWT                 JWT;
    private final JwtBlacklistService jwtBlacklistService;
    private final UserDetailsService  userDetailsService;

    private final String    userEmail = "b.smith@email.com";

    @Autowired
    public JwtBlacklistServiceTest(
            JWT jwt, JwtBlacklistService jwtBlacklistService, UserDetailsService userDetailsService
    ) {
        this.JWT = jwt;
        this.jwtBlacklistService = jwtBlacklistService;
        this.userDetailsService = userDetailsService;
    }

    @Test
    public void addJwtToBlacklistAndVerifyReadBack() {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        String jwt = JWT.createJWT(userDetails);

        jwtBlacklistService.blacklistJwt(jwt);

        assertTrue(jwtBlacklistService.jwtIsOnBlacklist(jwt));
    }
}
