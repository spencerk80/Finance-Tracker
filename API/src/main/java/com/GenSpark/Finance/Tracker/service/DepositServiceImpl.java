package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.DepositDao;
import com.GenSpark.Finance.Tracker.entity.Deposit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepositServiceImpl implements DepositService {
    private final DepositDao depositDao;

    public DepositServiceImpl(DepositDao depositDao) {
        this.depositDao = depositDao;
    }

    @Override
    public List<Deposit> getDeposits(int pageNo, int pageSize) {
        Page<Deposit> pagedResult = depositDao.findAll(PageRequest.of(pageNo, pageSize));
        if (pagedResult != null && pagedResult.hasContent()) return pagedResult.toList();
        else return new ArrayList<>();
    }

    @Override
    public void saveDeposit(Deposit deposit) {
        depositDao.save(deposit);
    }

    @Override
    public Deposit getDepositByID(int depositID) {
        Optional<Deposit> deposit = depositDao.findById(depositID);
        if (deposit.isPresent()) return deposit.get();
        else throw new RuntimeException("Deposit with ID: " + depositID + " not found.");
    }

    @Override
    public void updateDeposit(Deposit deposit) {
        depositDao.save(deposit);
    }

    @Override
    public void deleteDepositByID(int depositID) {
        depositDao.deleteById(depositID);
    }
}
