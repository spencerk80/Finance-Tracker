package com.GenSpark.Finance.Tracker.dao;

import com.GenSpark.Finance.Tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
