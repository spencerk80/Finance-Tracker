package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.EmailVerTokenDao;
import com.GenSpark.Finance.Tracker.entity.EmailVerToken;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class EmailVerTokenServiceImpl implements EmailVerTokenService {
    private static final BytesKeyGenerator  DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom();
    private static final Charset            UTF_8                   = StandardCharsets.UTF_8;

    private final EmailVerTokenDao          emailVerTokenDao;
    private final int                       TOKEN_VALIDITY_IN_SECONDS;

    @Autowired
    public EmailVerTokenServiceImpl(
            EmailVerTokenDao dao, @Value("${emailVerToken.validity.time}") int tokenValidityInSeconds
    ) {
        this.emailVerTokenDao = dao;
        this.TOKEN_VALIDITY_IN_SECONDS = tokenValidityInSeconds;
    }

    @Override
    public EmailVerToken createToken() {
        String tokenVal = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()));
        EmailVerToken token = new EmailVerToken();

        token.setToken(tokenVal);
        token.setExpiredAt(LocalDateTime.now().plusSeconds(TOKEN_VALIDITY_IN_SECONDS));
        saveToken(token);

        return token;
    }

    @Override
    public void saveToken(EmailVerToken token) {
        emailVerTokenDao.save(token);
    }

    @Override
    public EmailVerToken findByTokenStr(String token) {
        return emailVerTokenDao.findByToken(token);
    }

    @Override
    public void removeToken(EmailVerToken token) {
        emailVerTokenDao.delete(token);
    }

    @Override
    public void removeToken(String token) {
        emailVerTokenDao.removeByToken(token);
    }
}
