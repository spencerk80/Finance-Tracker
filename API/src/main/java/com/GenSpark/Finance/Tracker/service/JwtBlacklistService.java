package com.GenSpark.Finance.Tracker.service;

public interface JwtBlacklistService {
    void blacklistJwt(String jwt);
    boolean jwtIsOnBlacklist(String jwt);
}
