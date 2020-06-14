package businessLayer;

import dataLayer.entitites.Account;
import dataLayer.entitites.MyBank;
import dataLayer.entitites.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyBankTest {

    private MyBank myBank;
    private Account acc;

    @Before
    public void Setup() {
        Map<String, Account> mockCollection = new HashMap<>();
        User userMock = mock(User.class);
        when(userMock.getCprNumber()).thenReturn("1000");
        acc = new Account(mock(MyBank.class), userMock, "123");
        mockCollection.put(acc.getNumber(), acc);
        myBank = new MyBank("0", "testbank", mockCollection);
    }

    @After
    public void after(){
        myBank.setBankAccounts(new HashMap());
    }

    @Test
    public void testGetAccount() {
        Assert.assertEquals(myBank.getAccount("123"), acc);
    }

    @Test
    public void registerAccountTest() {
        Assert.assertNull(myBank.getAccount("1234"));
        User userMock = mock(User.class);
        when( userMock.getCprNumber() ).thenReturn("321");
        acc = new Account(mock(MyBank.class), userMock, "321");
        myBank.registerAccount(acc);
        Assert.assertEquals(myBank.getAccount("321"), acc);
    }

    @Test
    public void getUser() {
        User userMock = mock(User.class);
        when(userMock.getCprNumber()).thenReturn("12345");
        acc = new Account(mock(MyBank.class), userMock, "12345");
        myBank.registerAccount(acc);
        Assert.assertEquals(myBank.getUser("12345"), userMock);
    }

    @Test
    public void registerUserTest() {
        User user = new User("6789", "testUser");
        myBank.registerUser(user);
        Assert.assertEquals(myBank.getUser("6789"), user);
    }

    @Test
    public void getNameTest() {
        Assert.assertEquals(myBank.getName(), "testbank");
    }
}
