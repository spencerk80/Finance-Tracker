package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.JwtBlacklistDao;
import com.GenSpark.Finance.Tracker.entity.BlacklistedJwt;
import com.GenSpark.Finance.Tracker.util.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.ZoneId;

@Service
public class JwtBlacklistServiceImpl implements JwtBlacklistService {

    private JwtBlacklistDao jwtBlacklistDao;
    private JWT             JWT;

    @Autowired
    public JwtBlacklistServiceImpl(JwtBlacklistDao dao, JWT jwt) {
        jwtBlacklistDao = dao;
        this.JWT = jwt;
    }

    @Override
    public void blacklistJwt(String jwt) {
        BlacklistedJwt blacklistedJwt = new BlacklistedJwt(
                jwt,
                Date.from(JWT.getJwtExpiration(jwt).atZone(ZoneId.systemDefault()).toInstant())
        );

        jwtBlacklistDao.save(blacklistedJwt);
    }

    @Override
    public boolean jwtIsOnBlacklist(String jwt) {
        BlacklistedJwt blacklistedJwt = jwtBlacklistDao.findExpiredJwtByJwt(jwt);

        return blacklistedJwt != null;
    }
}
