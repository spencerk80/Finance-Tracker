package com.GenSpark.Finance.Tracker.dao;

import com.GenSpark.Finance.Tracker.entity.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawalDao extends JpaRepository<Withdrawal, Integer> {
    @Query(value = "SELECT * FROM withdrawal WHERE user_userid = ?1 LIMIT ?2, ?3", nativeQuery = true)
    List<Withdrawal> findAllByUserID(int userID, int pageNo, int pageSize);

    @Query(value = "SELECT FROM withdrawal WHERE user_userid = ?1 AND category_categoryID = ?2 LIMIT ?3, ?4", nativeQuery = true)
    List<Withdrawal> findAllByUserIdAndCatID(int userID, int catID, int pageNo, int pageSize);
}
