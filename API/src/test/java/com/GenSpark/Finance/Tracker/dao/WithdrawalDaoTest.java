package com.GenSpark.Finance.Tracker.dao;

import com.GenSpark.Finance.Tracker.entity.Category;
import com.GenSpark.Finance.Tracker.entity.Withdrawal;
import com.GenSpark.Finance.Tracker.entity.User;
import com.GenSpark.Finance.Tracker.enums.CategoryType;
import com.GenSpark.Finance.Tracker.enums.UserRole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WithdrawalDaoTest {
    @Autowired
    private WithdrawalDao withdrawalDao;
    private static Withdrawal testWithdrawal;

    @BeforeAll
    static void createWithdrawal() {
        Withdrawal withdrawal = new Withdrawal();

        withdrawal.setUser(
                new User(
                        "testPass123", "Test", "User",
                        "test.user@email.com", UserRole.USER, true
                )
        );
        withdrawal.setAmount(300);
        withdrawal.setCategory(new Category("Birthday Check", "From your grandma", CategoryType.WITHDRAWAL));
        withdrawal.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        withdrawal.setUserNote("Yay!!!!");

        testWithdrawal = withdrawal;
    }

    @Test
    public void saveWithdrawalTest() {
        withdrawalDao.save(testWithdrawal);

        //ID is set when saved to the db
        assertTrue(testWithdrawal.getWithdrawalID() > 0);
    }

    //This test requires pre-existing data in the db
    @Test
    public void getWithdrawalsByUserIDTest() {
        List<Withdrawal> withdrawals = withdrawalDao.findAllByUserID(0, 0, 10);

        assertTrue(withdrawals.size() > 0);
        assertTrue(withdrawals.size() <= 10);

        withdrawals.forEach(withdrawal -> {
            if(withdrawal.getUser().getUserID() != 0) fail();
        });
    }

    @Test
    public void getWithdrawalsByUserIdAndCatID() {
        List<Withdrawal> withdrawals = withdrawalDao.findAllByUserIdAndCatID(0, 0, 0, 10);

        assertTrue(withdrawals.size() > 0);
        assertTrue(withdrawals.size() <= 10);

        withdrawals.forEach(withdrawal -> {
            if(withdrawal.getUser().getUserID() != 0 || withdrawal.getCategory().getCategoryID() != 0) fail();
        });
    }

    @Test
    public void updateWithdrawalTest() {
        Optional<Withdrawal> retrievedWithdrawal;

        withdrawalDao.save(testWithdrawal);

        testWithdrawal.setAmount(20);
        withdrawalDao.save(testWithdrawal);

        retrievedWithdrawal = withdrawalDao.findById(testWithdrawal.getWithdrawalID());

        assertTrue(retrievedWithdrawal.isPresent());
        assertEquals(20, retrievedWithdrawal.get().getAmount());
    }

    @Test
    public void deleteWithdrawalTest() {
        Optional<Withdrawal> retrievedWithdrawal;

        withdrawalDao.save(testWithdrawal);
        withdrawalDao.deleteById(testWithdrawal.getWithdrawalID());
        retrievedWithdrawal = withdrawalDao.findById(testWithdrawal.getWithdrawalID());

        assertTrue(retrievedWithdrawal.isEmpty());
    }
}
