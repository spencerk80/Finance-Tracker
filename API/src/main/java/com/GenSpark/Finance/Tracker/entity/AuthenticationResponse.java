package com.GenSpark.Finance.Tracker.entity;

public class AuthenticationResponse {
    private String jwt;
    private User user;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String jwt, User user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("jwt: %s", jwt);
    }
}
