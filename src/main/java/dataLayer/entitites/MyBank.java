package dataLayer.entitites;

import businessLayer.Bank;
import businessLayer.Customer;

import java.util.Map;

public class MyBank extends Transferable implements Bank {

    private int cvr;
    private String name;
    private Map<Integer, Account> bankAccounts;

    public MyBank(int cvr, String name, Map<Integer, Account> bankAccounts) {
        this.cvr = cvr;
        this.name = name;
        this.bankAccounts = bankAccounts;
    }

    public void setCvr(int cvr) {
        this.cvr = cvr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Account> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(Map<Integer, Account> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    @Override
    public Account getAccount(int number) {
        return bankAccounts.get(number);
    }

    @Override
    public void registerAccount(Account account) {
        bankAccounts.put(account.getCustomer().getCprNumber(), account);
    }

    @Override
    public Customer getCustomer(int number) {
        return bankAccounts.get(number).getCustomer();
    }

    @Override
    public void registerCustomer(Customer customer) {
        Account acc = new Account(this, customer, customer.getCprNumber());
        bankAccounts.put(customer.getCprNumber(), acc);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCvr() {
        return this.cvr;
    }

}