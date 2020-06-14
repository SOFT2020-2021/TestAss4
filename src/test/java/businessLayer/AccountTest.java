package businessLayer;

import dataLayer.entitites.User;
import exceptions.AccountNotFoundException;
import dataLayer.entitites.Account;
import dataLayer.entitites.MyBank;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class AccountTest {

    MyBank bank;
    User user;
    String accountNumber;
    Account account;
    Account target;

    @Before
    public void setup(){
        bank = mock(MyBank.class);
        user = mock(User.class);
        accountNumber = "12345";
        String targetNumber = "12345";
        account = new Account(bank, user, accountNumber);
        target = new Account(bank, user, targetNumber);
        HashMap<String, Account> accounts = new HashMap<>();
        accounts.put(accountNumber,account);
        accounts.put(targetNumber, target);
        bank.setBankAccounts(accounts);
    }

    @After
    public void teardown(){
        bank = null;
        user = null;
        accountNumber = null;
        account = null;
        target = null;
    }

    @Test
    public void testCreateAccount() throws Exception {
        assertNotNull(account);
    }

    @Test
    public void testCreateAccountWithBank() {
        assertEquals(bank, account.getBank());
        assertNotNull(account.getBank());
    }

    @Test
    public void testCreateAccountWithNumber() {
        assertEquals(accountNumber, account.getNumber());
        assertNotNull(account.getNumber());
    }

    @Test
    public void testCreateAccountWithZeroBalance() {
        assertEquals(0L, account.getBalance());
    }

    @Test
    public void testTransferPositiveAmount() {
        int targetTransactionsSizeBefore = target.getTransactions().size();
        int sourceTransactionsSizeBefore = account.getTransactions().size();

        /*Exception exception = assertThrows(AccountNotFoundException.class, () -> {
            account.transfer(100, 1873123632);
        });*/

        when(bank.getAccount(anyString())).thenReturn(target);
        try {
            account.transfer(10000, target.getNumber());
            assertEquals(targetTransactionsSizeBefore + 1, target.getTransactions().size());
            assertEquals(sourceTransactionsSizeBefore + 1, account.getTransactions().size());
            assertEquals(-10000, account.getBalance());
            assertEquals(10000, target.getBalance());
        }catch(AccountNotFoundException e) {
            fail();
        }
    }
}