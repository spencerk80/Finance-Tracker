package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.Withdrawal;
import com.GenSpark.Finance.Tracker.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class WithdrawalController {

    private WithdrawalService withdrawalService;

    @Autowired
    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @GetMapping("/withdrawals/{withdrawalID}")
    public Withdrawal getWithdrawal(@PathVariable String withdrawalID) {
        return this.withdrawalService.getWithdrawalByID(Integer.parseInt(withdrawalID));
    }

    @PostMapping("/withdrawals")
    public void saveWithdrawal(@RequestBody Withdrawal category) {
        this.withdrawalService.saveWithdrawal(category);
    }

    @PutMapping("/withdrawals")
    public void updateWithdrawal(@RequestBody Withdrawal category) {
        this.withdrawalService.updateWithdrawal(category);
    }

    @DeleteMapping("/withdrawals/{withdrawalID}")
    public void deleteWithdrawal(@PathVariable String withdrawalID) {
        this.withdrawalService.deleteWithdrawalByID(Integer.parseInt(withdrawalID));
    }
}