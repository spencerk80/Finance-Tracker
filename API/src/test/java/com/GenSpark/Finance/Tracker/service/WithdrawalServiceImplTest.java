package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.WithdrawalDao;
import com.GenSpark.Finance.Tracker.entity.Withdrawal;
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
class WithdrawalServiceImplTest {

    @Mock
    private WithdrawalDao withdrawalDao;

    @InjectMocks
    private WithdrawalServiceImpl withdrawalService;

    private Withdrawal withdrawal;

    @Test
    void getAll() {
        Withdrawal withdrawalTest = new Withdrawal();
        Page<Withdrawal> withdrawalPage = new PageImpl<>(Collections.singletonList(withdrawalTest));
        when(withdrawalDao.findAll(PageRequest.of(0,1))).thenReturn(withdrawalPage);
        Page<Withdrawal> withdrawalPage1 = withdrawalDao.findAll(PageRequest.of(0,1));
        assertEquals(withdrawalPage1.getNumberOfElements(), 1);
    }

    @Test
    void saveWithdrawal() {
        withdrawalService.saveWithdrawal(withdrawal);
        verify(withdrawalDao, times(1)).save(withdrawal);
    }

    @Test
    void getWithdrawalByID() {
        int withdrawalID = 0;
        final Withdrawal withdrawal = new Withdrawal();
        given(withdrawalDao.findById(withdrawalID)).willReturn(Optional.of(withdrawal));
        Withdrawal expected = withdrawalService.getWithdrawalByID(withdrawalID);
        System.out.println(expected);
        assertThat(expected).isNotNull();
        assertEquals(withdrawal.getAmount(), expected.getAmount());
        assertEquals(withdrawal.getCategory(), expected.getCategory());
    }

    @Test
    void updateWithdrawal() {
        Withdrawal withdrawal = new Withdrawal();
        given(withdrawalDao.save(withdrawal)).willReturn(withdrawal);
        withdrawalService.updateWithdrawal(withdrawal);
        assertThat(withdrawal).isNotNull();
        verify(withdrawalDao).save(any(Withdrawal.class));
    }

    @Test
    void deleteWithdrawalByID() {
        int withdrawalID = 0;
        withdrawalService.deleteWithdrawalByID(withdrawalID);
        verify(withdrawalDao, times(1)).deleteById(withdrawalID);
    }

    @Test
    void getPageOfWithdrawalsByUserID() {
        Withdrawal withdrawalTest = new Withdrawal();
        Withdrawal withdrawalTest1 = new Withdrawal();
        Withdrawal withdrawalTest2 = new Withdrawal();
        List<Withdrawal> withdrawalList = new ArrayList<>();
        withdrawalList.add(withdrawalTest);
        withdrawalList.add(withdrawalTest1);
        withdrawalList.add(withdrawalTest2);
        when(withdrawalDao.findAllByUserID(0, 0, 3)).thenReturn(withdrawalList);
        List<Withdrawal> withdrawalList1 = withdrawalDao.findAllByUserID(0, 0, 3);
        Page<Withdrawal> withdrawalPage = new PageImpl<>(withdrawalList1);
        System.out.println(withdrawalPage.toList());
        assertEquals(withdrawalPage.getNumberOfElements(), 3);
    }

    @Test
    void getPageOfWithdrawalsByUserIdAndCatID() {
        Withdrawal withdrawalTest = new Withdrawal();
        Withdrawal withdrawalTest1 = new Withdrawal();
        Withdrawal withdrawalTest2 = new Withdrawal();
        List<Withdrawal> withdrawalList = new ArrayList<>();
        withdrawalList.add(withdrawalTest);
        withdrawalList.add(withdrawalTest1);
        withdrawalList.add(withdrawalTest2);
        when(withdrawalDao.findAllByUserIdAndCatID(0, 0, 0, 3)).thenReturn(withdrawalList);
        List<Withdrawal> withdrawalList1 = withdrawalDao.findAllByUserIdAndCatID(0, 0, 0, 3);
        Page<Withdrawal> withdrawalPage = new PageImpl<>(withdrawalList1);
        System.out.println(withdrawalPage.toList());
        assertEquals(withdrawalPage.getNumberOfElements(), 3);

    }
}