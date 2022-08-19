package com.GenSpark.Finance.Tracker.service;


import com.GenSpark.Finance.Tracker.dao.WithdrawalDao;
import com.GenSpark.Finance.Tracker.entity.Withdrawal;
import com.GenSpark.Finance.Tracker.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WithdrawalServiceImpl implements WithdrawalService{

    private final WithdrawalDao withdrawalDao;

    public WithdrawalServiceImpl(WithdrawalDao withdrawalDao) {
        this.withdrawalDao = withdrawalDao;
    }

    @Override
    public Withdrawal getWithdrawalByID(int withdrawalID) {
        return withdrawalDao.findById(withdrawalID).orElseThrow(() -> new ResourceNotFoundException("No Withdrawal Found With Id: " + withdrawalID));
    }

    @Override
    public String saveWithdrawal(Withdrawal withdrawal) {
        withdrawalDao.save(withdrawal);
        return "Successfully added the category";
    }

    @Override
    public String updateWithdrawal(Withdrawal withdrawal) {
        withdrawalDao.save(withdrawal);
        return "Successfully updated the category";
    }

    @Override
    public String deleteWithdrawalByID(int withdrawalID) {
        withdrawalDao.deleteById(withdrawalID);
        return "Successfully deleted the category";
    }

    @Override
    public List<Withdrawal> getAll(int pageNo, int pageSize) {
        Page<Withdrawal> pagedResult = withdrawalDao.findAll(PageRequest.of(pageNo, pageSize));
        if (pagedResult != null && pagedResult.hasContent()) return pagedResult.toList();
        else return new ArrayList<>();
    }

    @Override
    public List<Withdrawal> getPageOfWithdrawalsByUserID(int userID, int pageNo, int pageSize) {
        List<Withdrawal> withdrawalList = withdrawalDao.findAllByUserID(userID, pageNo, pageSize);
        Page<Withdrawal> pagedResult = new PageImpl<>(withdrawalList);
        if (pagedResult != null && pagedResult.hasContent()) return pagedResult.toList();
        else return new ArrayList<>();
    }

    @Override
    public List<Withdrawal> getPageOfWithdrawalsByUserIdAndCatID(int userID, int catID, int pageNo, int pageSize) {
        List<Withdrawal> withdrawalList = withdrawalDao.findAllByUserIdAndCatID(userID, catID, pageNo, pageSize);
        Page<Withdrawal> pagedResult = new PageImpl<>(withdrawalList);
        if (pagedResult != null && pagedResult.hasContent()) return pagedResult.toList();
        else return new ArrayList<>();
    }
}
