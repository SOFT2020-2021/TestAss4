package businessLayer;

import dataLayer.entitites.Account;
import dataLayer.entitites.MyBank;
import dataLayer.entitites.Transaction;
import dataLayer.entitites.User;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class TransactionTest {

    @Test
    public void createMovement(){

        long amount =1000L;
        long timestamp = 10000L;
        var acc = new Account(mock(MyBank.class), mock(User.class), 112);

        Transaction movementTest = new Transaction(acc, amount, timestamp);

        Assert.assertEquals(movementTest.getTimestamp(),timestamp  ) ;
        Assert.assertEquals(movementTest.getAmount(),amount);
        Assert.assertEquals(movementTest.getTarget(), acc);
    }
}