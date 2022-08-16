package com.GenSpark.Finance.Tracker.dao;

import com.GenSpark.Finance.Tracker.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositDao extends JpaRepository<Deposit, Integer> {
}
