package datalayer;

import dataLayer.DAO;
import dataLayer.entitites.Account;
import dataLayer.performers.AccountPerformer;
import dataLayer.performers.BankPerformer;
import dataLayer.performers.UserPerformer;
import org.junit.*;
import util.FileToString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountPerformerTest {

    AccountPerformer ap = new AccountPerformer();
    UserPerformer up = new UserPerformer();
    BankPerformer bp = new BankPerformer();

    @BeforeClass
    public static void setup() {
        try {
            DAO.connect("jdbc:postgresql://127.0.0.1:5432/bank");
            Statement st = DAO.connection.createStatement();

            st.executeUpdate("DROP DATABASE IF EXISTS testbank;");
            st.executeUpdate("CREATE DATABASE testbank;");

            st.close();
            DAO.close();
            DAO.connect("jdbc:postgresql://127.0.0.1:5432/testbank");

            String filePath = new File("").getAbsolutePath();
            String extension = "/src/test/scripts/create_tables_and_populate.sql";
            filePath += extension;
            String createDbQuery = FileToString.read(Paths.get(filePath));

            st = DAO.connection.createStatement();
            st.executeUpdate(createDbQuery);
            st.close();
            st = DAO.connection.createStatement();
            st.executeUpdate("DELETE FROM accounts WHERE 1 = 1;");
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterClass
    public static void teardown() throws SQLException {
        DAO.close();
        DAO.connect("jdbc:postgresql://127.0.0.1:5432/bank");
        Statement st = DAO.connection.createStatement();
        st.executeUpdate("DROP DATABASE IF EXISTS testbank;");
        st.close();
        DAO.close();

    }

    @After
    public void afterTest() throws SQLException, IOException {
        Statement statement = DAO.connection.createStatement();
        String filePath = new File("").getAbsolutePath();
        String extension = "/src/test/scripts/clean_test_db.sql";
        filePath += extension;
        String cleanTestDbQuery = FileToString.read(Paths.get(filePath));
        statement.execute(cleanTestDbQuery);
        statement.close();
    }

    @Test
    public void persistAndGetTest() {
        up.persist("765", "_");
        bp.persist("567", "_");
        Assert.assertNull(ap.get(1));
        ap.persist(100, "765", "567");
        //setup script populated db with 20 accounts
        Account account = ap.get(21);
        Assert.assertEquals(account.getBalance(), 100);
    }
}
