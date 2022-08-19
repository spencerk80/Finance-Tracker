package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.Withdrawal;
import com.GenSpark.Finance.Tracker.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WithdrawalController {

    private final WithdrawalService withdrawalService;

    @Autowired
    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    @GetMapping("/withdrawals/{withdrawalID}")
    public ResponseEntity<Withdrawal> getWithdrawal(@PathVariable String withdrawalID) {
        return ResponseEntity.ok().body(this.withdrawalService.getWithdrawalByID(Integer.parseInt(withdrawalID)));
    }

    @PostMapping("/withdrawals")
    public ResponseEntity<String> saveWithdrawal(@RequestBody Withdrawal category) {
        return ResponseEntity.ok().body(this.withdrawalService.saveWithdrawal(category));
    }

    @PutMapping("/withdrawals")
    public ResponseEntity<String> updateWithdrawal(@RequestBody Withdrawal category) {
        return ResponseEntity.ok().body(this.withdrawalService.updateWithdrawal(category));
    }

    @DeleteMapping("/withdrawals/{withdrawalID}")
    public ResponseEntity<String> deleteWithdrawal(@PathVariable String withdrawalID) {
        return ResponseEntity.ok().body(this.withdrawalService.deleteWithdrawalByID(Integer.parseInt(withdrawalID)));
    }
}
