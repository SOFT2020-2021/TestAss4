package dataLayer.performers;

import contract.transferables.UserTransaction;
import dataLayer.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionPerformer {

    public void createTransaction(long sender, long receiver, long amount) throws Exception {
        AccountPerformer ap = new AccountPerformer();
        ap.transferMoney(sender, receiver, amount);

        PreparedStatement ps = DAO.connection.prepareStatement(
                "INSERT INTO transactions (retriever,giver,amount) VALUES (?,?,?)");
        ps.setLong(1, receiver);
        ps.setLong(2, sender);
        ps.setLong(3, amount);
        ps.executeUpdate();
    }

    public List<UserTransaction> getTransactionByAccountId(int accountId) throws Exception {
        PreparedStatement ps = DAO.connection.prepareStatement(
                "SELECT cr.name as retriever_customer, cg.name as giver_customer, t.timestamp, t.amount \n" +
                    "FROM transactions t                                                                    \n" +
                    "JOIN accounts ar on ar.id = t.retriever                                                \n" +
                    "JOIN accounts ag on ag.id = t.giver                                                    \n" +
                    "JOIN customers cr on cr.cpr = ar.customercpr                                           \n" +
                    "JOIN customers cg on cg.cpr = ag.customercpr                                           \n" +
                    "WHERE t.retriever = ? OR t.giver = ?                                                   \n" +
                    "ORDER BY t.timestamp;"
        );

        ps.setInt(1, accountId);
        ps.setInt(2, accountId);
        ResultSet rs = ps.executeQuery();

        List<UserTransaction> userTransactions = new ArrayList();

        while(rs.next()){
          userTransactions.add(
                  new UserTransaction(
                          rs.getString(1),
                          rs.getString(2),
                          rs.getString(3),
                          rs.getLong(4)
                  ));
        }
        return userTransactions;
    }

}
