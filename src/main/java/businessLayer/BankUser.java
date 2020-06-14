package businessLayer;

import dataLayer.entitites.Account;

public interface BankUser {

    public boolean transfer(long money, Account account, Account target);

    public String getCprNumber();

    public String getName();

}
