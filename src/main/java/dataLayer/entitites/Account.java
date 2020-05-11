package dataLayer.entitites;

import exceptions.AccountNotFoundException;
import businessLayer.Bank;
import businessLayer.Customer;

import java.util.ArrayList;
import java.util.List;

public class Account extends Transferable {
    private Bank bank;
    private Customer customer;
    private int number;
    private long balance = 0;
    private List<Transaction> transactions = new ArrayList();

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Account(Bank bank, Customer customer, Integer number) {
        this.bank = bank;
        this.customer = customer;
        this.number = number;
    }

    public Bank getBank() {
        return bank;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Integer getNumber() {
        return number;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long insertionAmount) {
        balance += insertionAmount;
    }

    private long getUnixTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }

    public void transfer(long amount, Integer targetNumber) throws AccountNotFoundException {
        Account target = bank.getAccount(targetNumber);
        if (target == null) throw new AccountNotFoundException();
        balance -= amount;
        target.setBalance(target.getBalance() + amount);
        target.addTransaction(this, amount, getUnixTimestamp());
        amount *= -1;
        addTransaction(target, amount, getUnixTimestamp());
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Account target, long amount, long datetime) {
        transactions.add(new Transaction(target, amount, datetime));
    }
}