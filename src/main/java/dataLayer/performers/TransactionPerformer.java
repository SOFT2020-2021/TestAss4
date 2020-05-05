package dataLayer.performers;

import dataLayer.DAO;
import dataLayer.entitites.Transaction;

import java.sql.PreparedStatement;

public class TransactionPerformer {

    public void createTransaction (int sender, int receiver, long amount) throws Exception{
        AccountPerformer temp = new AccountPerformer();
        temp.transferMoney(sender, receiver, amount);

        PreparedStatement ps = DAO.connection.prepareStatement(
                "INSERT INTO transactions (retriever,giver,amount) VALUES (?,?,?)");
        ps.setInt(1,receiver);
        ps.setInt(2,sender);
        ps.setLong(3,amount);
        ps.executeUpdate();
}

    public Transaction get() {
        return null;
    }

    public boolean delete() {
        return false;
    }

    public boolean persist() {
        return false;
    }

    public boolean update() {
        return false;
    }
}
