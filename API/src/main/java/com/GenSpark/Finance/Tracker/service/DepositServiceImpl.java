package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.DepositDao;
import com.GenSpark.Finance.Tracker.entity.Deposit;
import com.GenSpark.Finance.Tracker.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepositServiceImpl implements DepositService {

    private final DepositDao depositDao;

    public DepositServiceImpl(DepositDao depositDao) {
        this.depositDao = depositDao;
    }

    @Override
    public Deposit getDepositByID(int depositID) {
        return depositDao.findById(depositID).orElseThrow(() -> new ResourceNotFoundException("No Deposit Found With Id: " + depositID));
    }

    @Override
    public String saveDeposit(Deposit deposit) {
        depositDao.save(deposit);
        return "Successfully added the category";
    }

    @Override
    public String updateDeposit(Deposit deposit) {
        depositDao.save(deposit);
        return "Successfully updated the category";
    }

    @Override
    public String deleteDepositByID(int depositID) {
        depositDao.deleteById(depositID);
        return "Successfully deleted the category";
    }

    @Override
    public List<Deposit> getDeposits(int pageNo, int pageSize) {
        Page<Deposit> pagedResult = depositDao.findAll(PageRequest.of(pageNo, pageSize));
        if (pagedResult != null && pagedResult.hasContent()) return pagedResult.toList();
        else return new ArrayList<>();
    }

    @Override
    public List<Deposit> getPageOfDepositsByUserID(int userID, int pageNo, int pageSize) {
        List<Deposit> depositList = depositDao.findAllByUserID(userID, pageNo, pageSize);
        Page<Deposit> pagedResult = new PageImpl<>(depositList);
        if (pagedResult != null && pagedResult.hasContent()) return pagedResult.toList();
        else return new ArrayList<>();
    }

    @Override
    public List<Deposit> getPageOfDepositsByUserIdAndCatID(int userID, int catID, int pageNo, int pageSize) {
        List<Deposit> depositList = depositDao.findAllByUserIdAndCatID(userID, catID, pageNo, pageSize);
        Page<Deposit> pagedResult = new PageImpl<>(depositList);
        if (pagedResult != null && pagedResult.hasContent()) return pagedResult.toList();
        else return new ArrayList<>();
    }
}
