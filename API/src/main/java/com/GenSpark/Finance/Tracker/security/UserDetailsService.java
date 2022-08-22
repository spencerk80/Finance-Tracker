package com.GenSpark.Finance.Tracker.security;

import com.GenSpark.Finance.Tracker.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserDao userDao;

    @Autowired
    public UserDetailsService(UserDao dao) {
        userDao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user;
        com.GenSpark.Finance.Tracker.entity.User userFromDB;
        List<GrantedAuthority> roles = new ArrayList<>();

        if(userDao.findUserByEmail(email).isEmpty()) throw new UsernameNotFoundException("User not found");

        userFromDB = userDao.findUserByEmail(email).get();
        roles.add(new SimpleGrantedAuthority(userFromDB.getRole().toString()));
        user = User.withUsername(userFromDB.getEmail())
                .password(userFromDB.getPassword())
                .authorities(roles)
                .disabled( ! userFromDB.isVerified())
                .build();

        return user;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        final int    strength    = 10;
        SecureRandom salt        = new SecureRandom();

        return new BCryptPasswordEncoder(strength, salt);
    }
}
