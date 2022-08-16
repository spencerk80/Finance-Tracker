package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.Deposit;
import com.GenSpark.Finance.Tracker.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class DepositController {
    @Autowired
    private DepositService depositService;

    @GetMapping("/deposits/{depositID}")
    public Deposit getDeposit(@PathVariable String depositID) {
        return this.depositService.getDepositByID(Integer.parseInt(depositID));
    }

    @PostMapping("/deposits")
    public Deposit saveDeposit(@RequestBody Deposit deposit) {
        return this.depositService.saveDeposit(deposit);
    }

    @PutMapping("/deposits")
    public Deposit updateDeposit(@RequestBody Deposit deposit) {
        return this.depositService.updateDeposit(deposit);
    }

    @DeleteMapping("/deposits/{depositId}")
    public Deposit deleteCategory(@PathVariable String depositId) {
        return this.depositService.deleteDepositByID(Integer.parseInt(depositId));
    }
}
