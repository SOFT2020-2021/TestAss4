package dataLayer.entitites;

import businessLayer.Bank;

import java.util.Map;

public class MyBank extends Transferable implements Bank {

    private String cvr;
    private String name;
    private Map<String, Account> bankAccounts;

    public MyBank(String cvr, String name, Map<String, Account> bankAccounts) {
        this.cvr = cvr;
        this.name = name;
        this.bankAccounts = bankAccounts;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Account> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(Map<String, Account> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @Override
    public Account getAccount(String number) {
        return bankAccounts.get(number);
    }

    @Override
    public void registerAccount(Account account) {
        bankAccounts.put(account.getUser().getCprNumber(), account);
    }

    @Override
    public User getUser(String number) {
        return bankAccounts.get(number).getUser();
    }

    @Override
    public void registerUser(User user) {
        Account acc = new Account(this, user, user.getCprNumber());
        bankAccounts.put(user.getCprNumber(), acc);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getCvr() {
        return this.cvr;
    }

}