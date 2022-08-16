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
    public Withdrawal saveWithdrawal(@RequestBody Withdrawal category) {
        return this.withdrawalService.saveWithdrawal(category);
    }

    @PutMapping("/withdrawals")
    public Withdrawal updateWithdrawal(@RequestBody Withdrawal category) {
        return this.withdrawalService.updateWithdrawal(category);
    }

    @DeleteMapping("/withdrawals/{withdrawalID}")
    public Withdrawal deleteWithdrawal(@PathVariable String withdrawalID) {
        return this.withdrawalService.deleteWithdrawalByID(Integer.parseInt(withdrawalID));
    }
}
