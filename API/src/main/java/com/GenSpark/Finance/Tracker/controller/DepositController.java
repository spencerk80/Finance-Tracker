package com.GenSpark.Finance.Tracker.controller;

import com.GenSpark.Finance.Tracker.entity.Deposit;
import com.GenSpark.Finance.Tracker.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepositController {

    private final DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping("/deposits")
    public ResponseEntity<List<Deposit>> getDeposits() {
        return ResponseEntity.ok().body(this.depositService.getDeposits(1, 10));
    }

    @GetMapping("/deposits/{depositID}")
    public ResponseEntity<Deposit> getDeposit(@PathVariable String depositID) {
        return ResponseEntity.ok().body(this.depositService.getDepositByID(Integer.parseInt(depositID)));
    }

    @PostMapping("/deposits")
    public ResponseEntity<String> saveDeposit(@RequestBody Deposit deposit) {
        return ResponseEntity.ok().body(this.depositService.saveDeposit(deposit));
    }

    @PutMapping("/deposits")
    public ResponseEntity<String> updateDeposit(@RequestBody Deposit deposit) {
        return ResponseEntity.ok().body(this.depositService.updateDeposit(deposit));
    }

    @DeleteMapping("/deposits/{depositId}")
    public ResponseEntity<String> deleteCategory(@PathVariable String depositId) {
        return ResponseEntity.ok().body(this.depositService.deleteDepositByID(Integer.parseInt(depositId)));
    }
}
