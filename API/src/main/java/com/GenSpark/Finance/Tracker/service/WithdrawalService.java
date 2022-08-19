package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.Withdrawal;
import java.util.List;

public interface WithdrawalService {

    Withdrawal getWithdrawalByID(int withdrawalID);
    String saveWithdrawal(Withdrawal withdrawal);
    String updateWithdrawal(Withdrawal withdrawal);
    String deleteWithdrawalByID(int withdrawalID);

    //Unused for now
    List<Withdrawal> getAll(int pageNo, int pageSize);
    List<Withdrawal> getPageOfWithdrawalsByUserID(int userID, int pageNo, int pageSize);
    List<Withdrawal> getPageOfWithdrawalsByUserIdAndCatID(int userID, int catID, int pageNo, int pageSize);
}
