package com.GenSpark.Finance.Tracker.dao;

import com.GenSpark.Finance.Tracker.entity.EmailVerToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerTokenDao extends JpaRepository<EmailVerToken, Long> {
    EmailVerToken findByToken(final String token);
    void removeByToken(final String token);
}
