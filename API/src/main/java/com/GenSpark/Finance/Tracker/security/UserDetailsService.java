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
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserDao userDao;

    @Autowired
    public UserDetailsService(UserDao dao) {
        userDao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user;
        com.GenSpark.Finance.Tracker.entity.User userFromDB = userDao.findUserByEmail(email).get();
        List<GrantedAuthority> roles = new ArrayList<>();

        roles.add(new SimpleGrantedAuthority(userFromDB.getRole().toString()));
        user = new User(userFromDB.getEmail(), userFromDB.getPassword(), roles);

        return user;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
//        final int       strength    = 10;
//        SecureRandom    salt        = new SecureRandom();
//
//        return new BCryptPasswordEncoder(strength, salt);

        return NoOpPasswordEncoder.getInstance();
    }
}
