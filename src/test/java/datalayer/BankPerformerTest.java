package datalayer;

import businessLayer.Bank;
import dataLayer.DAO;
import dataLayer.entitites.MyBank;
import dataLayer.performers.BankPerformer;
import org.junit.*;

import java.sql.SQLException;
import java.sql.Statement;

public class BankPerformerTest {

    BankPerformer bp = new BankPerformer();

    @BeforeClass
    public static void setup() {
        try {
            DAO.connect("jdbc:postgresql://localhost:5432/bank");
            Statement st = DAO.connection.createStatement();

            st.executeUpdate("DROP DATABASE IF EXISTS testbank;");
            st.executeUpdate("CREATE DATABASE testbank;");

            st.close();
            DAO.close();
            DAO.connect("jdbc:postgresql://localhost:5432/testbank");

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
        DAO.connect("jdbc:postgresql://localhost:5432/bank");
        Statement st = DAO.connection.createStatement();
        st.executeUpdate("DROP DATABASE IF EXISTS testbank;");
        st.close();
        DAO.close();

    }

    @After
    public void afterTest() throws SQLException {
        Statement statement = DAO.connection.createStatement();
        statement.execute("DELETE FROM banks WHERE 1=1;");
    }

    @Test
    public void persistAndGetTest() {
        MyBank bank = null;
        Assert.assertNull(bp.get(345));
        bp.persist(345, "Hvidvasken");
        Assert.assertEquals(bp.get(345).getName(), "Hvidvasken");
        Assert.assertEquals(bp.get(345).getCvr(), 345);
    }

    @Test
    public void updateTest() {
        bp.persist(100, "Vaskeriet");
        Bank bank = bp.get(100);
        Assert.assertEquals(bank.getName(), "Vaskeriet");
        bp.update(100,200,"Vandmøllen");
        bank = bp.get(200);
        Assert.assertEquals(bank.getName(), "Vandmøllen");
    }

    @Test
    public void deleteTest() {
        bp.persist(200, "Vandmøllen");
        Bank bank = bp.get(200);
        Assert.assertEquals(bank.getName(), "Vandmøllen");
        bp.delete(200);
        bank = bp.get(100);
        Assert.assertNull(bank);
    }
}
