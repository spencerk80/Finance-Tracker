package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.Deposit;

public interface DepositService {
    void saveDeposit(Deposit deposit);
    Deposit getDepositByID(int depositID);
    void updateDeposit(Deposit deposit);
    void deleteDepositByID(int depositID);
}
