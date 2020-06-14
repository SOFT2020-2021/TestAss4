package businessLayer.controllers;

import businessLayer.Bank;
import contract.Contract;
import contract.transferables.AccountTransferable;
import contract.transferables.UserWithAccountsTransferable;
import contract.transferables.SimpleAccountTransferable;
import dataLayer.entitites.Account;
import dataLayer.entitites.MyBank;
import dataLayer.entitites.User;
import dataLayer.performers.AccountPerformer;
import java.util.HashMap;
import java.util.List;

public class AccountController {

    public Contract getAccount(String number){
        User user = new User("2201111", "Sven");
        Bank bank = new MyBank("321321", "Nordea", new HashMap<String, Account>());
        Account acc = new Account(bank, user, number);
        return new AccountTransferable(acc.getUser().getCprNumber(),bank.getCvr(), number, acc.getBalance());
    }


    public String getAccountsbyUserId (String cpr) throws Exception {
        AccountPerformer ap = new AccountPerformer();
        List<SimpleAccountTransferable> alle = ap.getAllAccountsForUser(cpr);
        Contract<UserWithAccountsTransferable> temp = new UserWithAccountsTransferable(cpr, alle);
        return temp.toJSON();
    }

}
