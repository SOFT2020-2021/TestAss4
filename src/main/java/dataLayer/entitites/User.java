package dataLayer.entitites;

import businessLayer.Customer;

public class User extends Transferable implements Customer {

    private int cpr;
    private String name;

    public User(int cpr, String name) {
        this.cpr = cpr;
        this.name = name;
    }

    public void setCpr(int cpr) {
        this.cpr = cpr;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean transfer(long money, Account account, Account target) {
        try {
            account.transfer(money, target.getNumber());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getCprNumber() {
        return this.cpr;
    }

    @Override
    public String getName() {
        return this.name;
    }

}

