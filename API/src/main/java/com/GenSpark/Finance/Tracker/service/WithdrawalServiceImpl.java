package com.GenSpark.Finance.Tracker.service;


import com.GenSpark.Finance.Tracker.dao.WithdrawalDao;
import com.GenSpark.Finance.Tracker.entity.Withdrawal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WithdrawalServiceImpl implements WithdrawalService{

    private WithdrawalDao withdrawalDao;

    public WithdrawalServiceImpl(WithdrawalDao withdrawalDao) {
        this.withdrawalDao = withdrawalDao;
    }

    @Override
    public List<Withdrawal> getAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Withdrawal> pagedResult = withdrawalDao.findAll(paging);
        if (pagedResult.hasContent()) return pagedResult.getContent();
        else return new ArrayList<>();
    }

    @Override
    public void saveWithdrawal(Withdrawal withdrawal) {
        withdrawalDao.save(withdrawal);
    }

    @Override
    public Withdrawal getWithdrawalByID(int withdrawalID) {
        Optional<Withdrawal> withdrawal = withdrawalDao.findById(withdrawalID);
        if (withdrawal.isPresent()) return withdrawal.get();
        else throw new RuntimeException("Withdrawal with ID: " + withdrawalID + " not found.");
    }

    @Override
    public void updateWithdrawal(Withdrawal withdrawal) {
        withdrawalDao.save(withdrawal);
    }

    @Override
    public void deleteWithdrawalByID(int withdrawalID) {
        withdrawalDao.deleteById(withdrawalID);
    }
}
