package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.DepositDao;
import com.GenSpark.Finance.Tracker.entity.Deposit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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