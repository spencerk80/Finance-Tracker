package com.GenSpark.Finance.Tracker.dao;

import com.GenSpark.Finance.Tracker.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositDao extends JpaRepository<Deposit, Integer> {
    @Query(value = "SELECT * FROM deposit WHERE user_userid = ?1 LIMIT ?2, ?3", nativeQuery = true)
    List<Deposit> findAllByUserID(int userID, int pageNo, int pageSize);

    @Query(value = "SELECT * FROM deposit WHERE user_userid = ?1 AND category_categoryid = ?2 LIMIT ?3, ?4", nativeQuery = true)
    List<Deposit> findAllByUserIdAndCatID(int userID, int catID, int pageNo, int pageSize);
}
