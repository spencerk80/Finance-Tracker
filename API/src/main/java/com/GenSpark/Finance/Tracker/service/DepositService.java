package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.Deposit;

import java.util.List;

public interface DepositService {
    List<Deposit> getDeposits(Integer pageNo, Integer pageSize);
    void saveDeposit(Deposit deposit);
    Deposit getDepositByID(int depositID);
    void updateDeposit(Deposit deposit);
    void deleteDepositByID(int depositID);
}
