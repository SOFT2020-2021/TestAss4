package datalayer;

import dataLayer.DAO;
import dataLayer.entitites.User;
import dataLayer.performers.CustomersPerformer;
import org.junit.*;

import java.sql.SQLException;
import java.sql.Statement;

public class UserPerformerTest {

    CustomersPerformer up = new CustomersPerformer();

    @BeforeClass
    public static void setup() {
        try {
            DAO.connect("jdbc:postgresql://localhost:5432/testbank");
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
                    "    giver     INT " +
                    " accounts (id) NOT NULL,\n" +
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
        statement.execute("DELETE FROM users WHERE 1=1;");
    }

    @Test
    public void persistAndGetTest() {
        /*Assert.assertNull(up.get(100));
        up.persist(100, "Christian");
        User user = up.get(100);
        Assert.assertEquals(user.getCprNumber(), 100);
        Assert.assertEquals(user.getName(),"Christian");*/
    }

    @Test
    public void updateTest() {
        /*up.persist(100, "Christian");
        User user = up.get(100);
        Assert.assertEquals(user.getName(), "Christian");
        up.update(100,100,"Alexander");
        user = up.get(100);
        Assert.assertEquals(user.getName(), "Alexander");*/
    }

    @Test
    public void deleteTest() {
        /*up.persist(100, "Christian");
        User user = up.get(100);
        Assert.assertEquals(user.getName(), "Christian");
        up.delete(100);
        user = up.get(100);
        Assert.assertNull(user);*/
    }
}
