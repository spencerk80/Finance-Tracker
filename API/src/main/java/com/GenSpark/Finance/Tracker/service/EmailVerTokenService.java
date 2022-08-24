package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.EmailVerTokenDao;
import com.GenSpark.Finance.Tracker.entity.EmailVerToken;

public interface EmailVerTokenService {
    EmailVerToken createToken();
    void saveToken(EmailVerToken token);
    EmailVerToken findByTokenStr(String token);
    void removeToken(EmailVerToken token);
    void removeToken(String token);
}
