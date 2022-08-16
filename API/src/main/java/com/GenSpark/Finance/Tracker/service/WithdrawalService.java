package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.Withdrawal;

public interface WithdrawalService {
    Withdrawal saveWithdrawal(Withdrawal withdrawal);
    Withdrawal getWithdrawalByID(int withdrawalID);
    Withdrawal updateWithdrawal(Withdrawal withdrawal);
    Withdrawal deleteWithdrawalByID(int withdrawalID);
}
