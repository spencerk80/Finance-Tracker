package com.GenSpark.Finance.Tracker.dao;

import com.GenSpark.Finance.Tracker.entity.Category;
import com.GenSpark.Finance.Tracker.entity.Deposit;
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
public class DepositDaoTest {
    @Autowired
    private DepositDao depositDao;
    private static Deposit testDeposit;

    @BeforeAll
    static void createDeposit() {
        Deposit deposit = new Deposit();

        deposit.setUser(
                new User(
                        "testPass123", "Test", "User",
                        "test.user@email.com", UserRole.USER, true
                )
        );
        deposit.setAmount(300);
        deposit.setCategory(new Category("Birthday Check", "From your grandma", CategoryType.DEPOSIT));
        deposit.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        deposit.setUserNote("Yay!!!!");

        testDeposit = deposit;
    }

    @Test
    public void saveDepositTest() {
        depositDao.save(testDeposit);

        //ID is set when saved to the db
        assertTrue(testDeposit.getDepositID() > 0);
    }

    //This test requires pre-existing data in the db
    @Test
    public void getDepositsByUserIDTest() {
        List<Deposit> deposits = depositDao.findAllByUserID(0, 0, 10);

        assertTrue(deposits.size() > 0);
        assertTrue(deposits.size() <= 10);

        deposits.forEach(deposit -> {
            if(deposit.getUser().getUserID() != 0) fail();
        });
    }

    @Test
    public void getDepositsByUserIdAndCatID() {
        List<Deposit> deposits = depositDao.findAllByUserIdAndCatID(0, 3, 0, 10);

        assertTrue(deposits.size() > 0);
        assertTrue(deposits.size() <= 10);

        deposits.forEach(deposit -> {
            if(deposit.getUser().getUserID() != 0 || deposit.getCategory().getCategoryID() != 3) fail();
        });
    }

    @Test
    public void updateDepositTest() {
        Optional<Deposit> retrievedDeposit;

        depositDao.save(testDeposit);

        testDeposit.setAmount(20);
        depositDao.save(testDeposit);

        retrievedDeposit = depositDao.findById(testDeposit.getDepositID());

        assertTrue(retrievedDeposit.isPresent());
        assertEquals(20, retrievedDeposit.get().getAmount());
    }

    @Test
    public void deleteDepositTest() {
        Optional<Deposit> retrievedDeposit;

        depositDao.save(testDeposit);
        depositDao.deleteById(testDeposit.getDepositID());
        retrievedDeposit = depositDao.findById(testDeposit.getDepositID());

        assertTrue(retrievedDeposit.isEmpty());
    }
}
