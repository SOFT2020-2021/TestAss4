package businessLayer;

import Exceptions.AccountNotFoundException;
import dataLayer.entitites.Account;

public interface Bank {

    Account getAccount(int number) throws AccountNotFoundException;

    void registerAccount(Account account);

    Customer getCustomer(int number);

    void registerCustomer(Customer customer);

    String getName();

    int getCvr();

}