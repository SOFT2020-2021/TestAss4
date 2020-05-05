package datalayer;

import dataLayer.DAO;
import dataLayer.entitites.Account;
import dataLayer.performers.AccountPerformer;
import dataLayer.performers.BankPerformer;
import dataLayer.performers.CustomersPerformer;
import org.junit.*;

import java.sql.SQLException;
import java.sql.Statement;

public class AccountPerformerTest {

    AccountPerformer ap = new AccountPerformer();
    CustomersPerformer up = new CustomersPerformer();
    BankPerformer bp = new BankPerformer();

    @BeforeClass
    public static void setup() {
        try {
            DAO.connect("jdbc:postgresql://192.168.1.137:5432/bank");
            Statement st = DAO.connection.createStatement();

            st.executeUpdate("DROP DATABASE IF EXISTS testbank;");
            st.executeUpdate("CREATE DATABASE testbank;");

            st.close();
            DAO.close();
            DAO.connect("jdbc:postgresql://192.168.1.137:5432/testbank");

            st = DAO.connection.createStatement();
            st.executeUpdate("CREATE TABLE users\n" +
                    "(\n" +
                    "    cpr  INT PRIMARY KEY,\n" +
                    "    name VARCHAR(100) NOT NULL\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE banks\n" +
                    "(\n" +
                    "    cvr  INT PRIMARY KEY,\n" +
                    "    name VARCHAR(100) NOT NULL\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE accounts\n" +
                    "(\n" +
                    "    id          SERIAL PRIMARY KEY,\n" +
                    "    balance     INT,\n" +
                    "    customerCpr INT references users (cpr) NOT NULL,\n" +
                    "    bankCvr     INT references banks (cvr) NOT NULL\n" +
                    ");\n" +
                    "\n" +
                    "CREATE TABLE transactions\n" +
                    "(\n" +
                    "    id        SERIAL PRIMARY KEY,\n" +
                    "    retriever INT references accounts (id) NOT NULL,\n" +
                    "    giver     INT references accounts (id) NOT NULL,\n" +
                    "    amount    INT                          NOT NULL\n" +
                    ");");
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterClass
    public static void teardown() throws SQLException {
        DAO.close();
        DAO.connect("jdbc:postgresql://192.168.1.137:5432/bank");
        Statement st = DAO.connection.createStatement();
        st.executeUpdate("DROP DATABASE IF EXISTS testbank;");
        st.close();
        DAO.close();

    }

    @After
    public void afterTest() throws SQLException {
        Statement statement = DAO.connection.createStatement();
        statement.execute("DELETE FROM accounts WHERE 1=1;");
        statement.execute("DELETE FROM users WHERE 1=1;");
        statement.execute("DELETE FROM banks WHERE 1=1;");
    }

    @Test
    public void persistAndGetTest() {
        up.persist(765, "Loltest");
        bp.persist(567, "Loltest");
        Assert.assertNull(ap.get(1));
        ap.persist(100, 765, 567);
        Account account = ap.get(1);
        Assert.assertEquals(account.getBalance(), 100);
    }

    @Test
    public void updateTest() {

    }

    @Test
    public void deleteTest() {

    }
}
