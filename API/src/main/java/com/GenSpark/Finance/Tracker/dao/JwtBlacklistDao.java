package com.GenSpark.Finance.Tracker.dao;

import com.GenSpark.Finance.Tracker.entity.BlacklistedJwt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtBlacklistDao extends JpaRepository<BlacklistedJwt, Long> {
    BlacklistedJwt findExpiredJwtByJwt(String jwt);
}
