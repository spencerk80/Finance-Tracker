package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.AuthenticationRequest;
import com.GenSpark.Finance.Tracker.entity.AuthenticationResponse;
import com.GenSpark.Finance.Tracker.security.UserDetailsService;
import com.GenSpark.Finance.Tracker.util.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private AuthenticationManager   authManager;
    private UserDetailsService      userDetailsService;

    @Autowired
    public AuthController(AuthenticationManager authManager, UserDetailsService userDetailsService) {
        this.authManager = authManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/authenticate")
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

}
