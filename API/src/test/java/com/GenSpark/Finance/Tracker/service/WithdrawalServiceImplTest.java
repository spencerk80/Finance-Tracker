package com.GenSpark.Finance.Tracker.service;

import com.GenSpark.Finance.Tracker.dao.WithdrawalDao;
import com.GenSpark.Finance.Tracker.entity.Withdrawal;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WithdrawalServiceImplTest {

    @Mock
    private WithdrawalDao withdrawalDao;

    @InjectMocks
    private WithdrawalServiceImpl withdrawalService;

    private Withdrawal withdrawal;

    @Test
    void getAll() {
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
}