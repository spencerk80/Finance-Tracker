package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.entity.Deposit;

import java.util.List;

public interface DepositService {

    Deposit getDepositByID(int depositID);
    String saveDeposit(Deposit deposit);
    String updateDeposit(Deposit deposit);
    String deleteDepositByID(int depositID);

    //Unused for now
    List<Deposit> getDeposits(int pageNo, int pageSize);
    List<Deposit> getPageOfDepositsByUserID(int userID, int pageNo, int pageSize);
    List<Deposit> getPageOfDepositsByUserIdAndCatID(int userID, int catID, int pageNo, int pageSize);
}
