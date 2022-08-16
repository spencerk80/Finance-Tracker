package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.Deposit;

public interface DepositService {
    Deposit saveDeposit(Deposit deposit);
    Deposit getDepositByID(int depositID);
    Deposit updateDeposit(Deposit deposit);
    Deposit deleteDepositByID(int depositID);
}
