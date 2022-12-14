package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.AuthenticationRequest;
import com.GenSpark.Finance.Tracker.entity.AuthenticationResponse;
import com.GenSpark.Finance.Tracker.entity.EmailVerToken;
import com.GenSpark.Finance.Tracker.entity.User;
import com.GenSpark.Finance.Tracker.security.UserDetailsService;
import com.GenSpark.Finance.Tracker.service.EmailVerTokenService;
import com.GenSpark.Finance.Tracker.service.JwtBlacklistService;
import com.GenSpark.Finance.Tracker.service.UserService;
import com.GenSpark.Finance.Tracker.util.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class AuthController {

    private final AuthenticationManager   authManager;
    private final UserDetailsService      userDetailsService;
    private final EmailVerTokenService    emailVerTokenService;
    private final UserService             userService;
    private final JWT                     JWT;
    private final JwtBlacklistService     jwtBlacklistService;

    @Autowired
    public AuthController(
            AuthenticationManager authManager, UserDetailsService userDetailsService,
            EmailVerTokenService emailVerTokenService, UserService userService, JWT jwt,
            JwtBlacklistService jwtBlacklistService
    ) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.emailVerTokenService = emailVerTokenService;
        this.userService = userService;
        this.JWT = jwt;
        this.jwtBlacklistService = jwtBlacklistService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> authUser(@RequestBody AuthenticationRequest authRequest) {
        UserDetails userDetails;
        User        user;
        AuthenticationResponse response = new AuthenticationResponse();

        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch(BadCredentialsException e) {
            return ResponseEntity.status(401).body(response);
        }

        userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        user = userService.getUserByEmail(userDetails.getUsername());

        response.setJwt(JWT.createJWT(userDetails));
        response.setUser(user);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<String> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt) {
        jwtBlacklistService.blacklistJwt(jwt.substring(7));

        return ResponseEntity.ok().body("Bye-bye");
    }

    @GetMapping("/auth/verify/{token}")
    public void EmailVerifyLinkProcessing(@PathVariable String token) {
        EmailVerToken   emailVerToken   = emailVerTokenService.findByTokenStr(token);
        User            user            = emailVerToken.getUser();
        LocalDateTime   now             = LocalDateTime.now();

        //Token expired
        if(emailVerToken.getExpiredAt().isBefore(now)) return;

        user.setVerified(true);
        userService.updateUser(user);
        emailVerTokenService.removeToken(token);
    }
}
