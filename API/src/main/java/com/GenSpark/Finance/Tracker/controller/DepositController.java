package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.Deposit;
import com.GenSpark.Finance.Tracker.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class DepositController {

    private DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping("/deposits/{depositID}")
    public Deposit getDeposit(@PathVariable String depositID) {
        return this.depositService.getDepositByID(Integer.parseInt(depositID));
    }

    @PostMapping("/deposits")
    public void saveDeposit(@RequestBody Deposit deposit) {
        this.depositService.saveDeposit(deposit);
    }

    @PutMapping("/deposits")
    public void updateDeposit(@RequestBody Deposit deposit) {
        this.depositService.updateDeposit(deposit);
    }

    @DeleteMapping("/deposits/{depositId}")
    public void deleteDeposit(@PathVariable String depositId) {
        this.depositService.deleteDepositByID(Integer.parseInt(depositId));
    }
}
