package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.DepositDao;
import com.GenSpark.Finance.Tracker.entity.Deposit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepositServiceImplTest {


    @Mock
    private DepositDao depositDao;

    @InjectMocks
    private DepositServiceImpl depositService;

    private Deposit deposit;


    @Test
    void getDeposits() {
        Deposit deposit1 = new Deposit();
        Deposit deposit2 = new Deposit();
        Deposit deposit3 = new Deposit();
        Deposit deposit4 = new Deposit();
        Deposit deposit5 = new Deposit();
        List<Deposit> deposits = new ArrayList<>();
        deposits.add(deposit1);
        deposits.add(deposit2);
        deposits.add(deposit3);
        deposits.add(deposit4);
        deposits.add(deposit5);
        System.out.println(deposits);
    }

    @Test
    void saveDeposit() {
        depositService.saveDeposit(deposit);
        verify(depositDao, times(1)).save(deposit);
    }

    @Test
    void getDepositByID() {
        int depositID = 0;
        final Deposit deposit = new Deposit();
        given(depositDao.findById(depositID)).willReturn(Optional.of(deposit));
        Deposit expected = depositService.getDepositByID(depositID);
        System.out.println(expected);
        Assertions.assertThat(expected).isNotNull();
        assertEquals(deposit.getAmount(), expected.getAmount());
        assertEquals(deposit.getCategory(), expected.getCategory());
    }

    @Test
    void updateDeposit() {
        Deposit updatedDeposit = new Deposit();
        given(depositDao.save(updatedDeposit)).willReturn(updatedDeposit);
        depositService.updateDeposit(updatedDeposit);
        assertThat(updatedDeposit).isNotNull();
        verify(depositDao).save(any(Deposit.class));
    }

    @Test
    void deleteDepositByID() {
        int depositID = 0;
        depositService.deleteDepositByID(depositID);
        verify(depositDao, times(1)).deleteById(depositID);
    }
}