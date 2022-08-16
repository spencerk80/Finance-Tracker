package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.Withdrawal;

public interface WithdrawalService {
    void saveWithdrawal(Withdrawal withdrawal);
    Withdrawal getWithdrawalByID(int withdrawalID);
    void updateWithdrawal(Withdrawal withdrawal);
    void deleteWithdrawalByID(int withdrawalID);
}
