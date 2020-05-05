package businessLayer;

import dataLayer.entitites.Account;

public interface Customer {

    public boolean transfer(long money, Account account, Account target);

    public int getCprNumber();

    public String getName();

}
