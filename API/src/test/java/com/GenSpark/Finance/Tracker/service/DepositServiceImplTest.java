package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.DepositDao;
import com.GenSpark.Finance.Tracker.entity.Deposit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepositServiceImplTest {


    @Mock
    private DepositDao depositDao;

    @InjectMocks
    private DepositServiceImpl depositService;

    private Deposit deposit;


    @Test
    void getDeposits() {
        Deposit depositTest = new Deposit();
        Page<Deposit> depositPage = new PageImpl<>(Collections.singletonList(depositTest));
        when(depositDao.findAll(PageRequest.of(0,1))).thenReturn(depositPage);
        Page<Deposit> depositPage1 = depositDao.findAll(PageRequest.of(0,1));
        assertEquals(depositPage1.getNumberOfElements(), 1);
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

    @Test
    void getPageOfDepositsByUserID() {
        Deposit depositTest = new Deposit();
        Deposit depositTest1 = new Deposit();
        Deposit depositTest2 = new Deposit();
        List<Deposit> depositList = new ArrayList<>();
        depositList.add(depositTest);
        depositList.add(depositTest1);
        depositList.add(depositTest2);
        when(depositDao.findAllByUserID(0, 0, 3)).thenReturn(depositList);
        List<Deposit> depositList1 = depositDao.findAllByUserID(0, 0, 3);
        Page<Deposit> depositPage = new PageImpl<>(depositList1);
        System.out.println(depositPage.toList());
        assertEquals(depositPage.getNumberOfElements(), 3);
    }

    @Test
    void getPageOfDepositsByUserIdAndCatID() {
        Deposit depositTest = new Deposit();
        Deposit depositTest1 = new Deposit();
        Deposit depositTest2 = new Deposit();
        List<Deposit> depositList = new ArrayList<>();
        depositList.add(depositTest);
        depositList.add(depositTest1);
        depositList.add(depositTest2);
        when(depositDao.findAllByUserIdAndCatID(0,0, 0, 3)).thenReturn(depositList);
        List<Deposit> depositList1 = depositDao.findAllByUserIdAndCatID(0,0, 0, 3);
        Page<Deposit> depositPage = new PageImpl<>(depositList1);
        System.out.println(depositPage.toList());
        assertEquals(depositPage.getNumberOfElements(), 3);
    }
}