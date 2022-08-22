package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.AuthenticationRequest;
import com.GenSpark.Finance.Tracker.entity.AuthenticationResponse;
import com.GenSpark.Finance.Tracker.security.UserDetailsService;
import com.GenSpark.Finance.Tracker.util.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private AuthenticationManager   authManager;
    private UserDetailsService      userDetailsService;
    private JWT                     JWT;

    @Autowired
    public AuthController(AuthenticationManager authManager, UserDetailsService userDetailsService, JWT jwt) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
        this.JWT = jwt;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> authUser(@RequestBody AuthenticationRequest authRequest) {
        UserDetails userDetails;
        AuthenticationResponse response = new AuthenticationResponse();

        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch(BadCredentialsException e) {
            return ResponseEntity.status(401).body(response);
        }

        userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        response.setJwt(JWT.createJWT(userDetails));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/logout")
    public ResponseEntity<String> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt) {
        JWT.blacklistJwt(jwt);

        return ResponseEntity.ok().body("Bye-bye");
    }
}
