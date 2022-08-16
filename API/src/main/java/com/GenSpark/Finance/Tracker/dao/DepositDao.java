package com.GenSpark.Finance.Tracker.dao;

import com.GenSpark.Finance.Tracker.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositDao extends JpaRepository<Deposit, Integer> {
    @Query(value = "SELECT * FROM deposit WHERE user_userid = ?1 LIMIT ?2, ?3")
    List<Deposit> getPageOfDepositsByUserID(int userID, int page, int pageLimit);

    @Query(value = "SELECT FROM deposit WHERE user_userid = ?1 AND category_categoryID = ?2 LIMIT ?3, ?4")
    List<Deposit> getPageOfDepositsByUserIdAndCatID(int userID, int catID, int page, int pageLimit);
}
