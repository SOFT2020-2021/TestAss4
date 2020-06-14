package businessLayer;

import dataLayer.entitites.User;
import exceptions.AccountNotFoundException;
import dataLayer.entitites.Account;

public interface Bank {

    Account getAccount(String number) throws AccountNotFoundException;

    void registerAccount(Account account);

    User getUser(String number);

    void registerUser(User user);

    String getName();

    String getCvr();

}