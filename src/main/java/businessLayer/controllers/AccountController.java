package businessLayer.controllers;

import businessLayer.Bank;
import businessLayer.Customer;
import contract.Contract;
import contract.transferables.AccountTransferable;
import contract.transferables.CustomerWithAccountsTransferable;
import contract.transferables.SimpleAccountTransferable;
import dataLayer.entitites.Account;
import dataLayer.entitites.MyBank;
import dataLayer.entitites.User;
import dataLayer.performers.AccountPerformer;
import java.util.HashMap;
import java.util.List;

public class AccountController {

    public Contract getAccount(int number){
        Customer customer = new User(2201111, "Sven");
        Bank bank = new MyBank(321321, "Nordea", new HashMap<Integer, Account>());
        Account acc = new Account(bank, customer, number);
        return new AccountTransferable(acc.getCustomer().getCprNumber(),bank.getCvr(), number, acc.getBalance());
    }


    public String getAccountsByCustomerID (String cpr) throws Exception {
        AccountPerformer test = new AccountPerformer();
        List<SimpleAccountTransferable> alle = test.getAllAccountsForCustomer(cpr);
        Contract<CustomerWithAccountsTransferable> temp = new CustomerWithAccountsTransferable(cpr, alle);
        return temp.toJSON();
    }

}
