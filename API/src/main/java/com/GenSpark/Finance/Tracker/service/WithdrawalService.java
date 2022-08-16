package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.Withdrawal;

import java.util.List;

public interface WithdrawalService {
    List<Withdrawal> getAll(int pageNo, int pageSize);
    void saveWithdrawal(Withdrawal withdrawal);
    Withdrawal getWithdrawalByID(int withdrawalID);
    void updateWithdrawal(Withdrawal withdrawal);
    void deleteWithdrawalByID(int withdrawalID);
}
