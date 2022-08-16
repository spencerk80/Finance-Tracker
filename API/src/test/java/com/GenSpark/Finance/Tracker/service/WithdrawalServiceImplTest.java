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
    }

    @Test
    void updateWithdrawal() {
    }

    @Test
    void deleteWithdrawalByID() {
    }
}