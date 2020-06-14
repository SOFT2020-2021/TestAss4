package datalayer;

import businessLayer.Bank;
import dataLayer.DAO;
import dataLayer.entitites.MyBank;
import dataLayer.performers.BankPerformer;
import org.junit.*;
import util.FileToString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class BankPerformerTest {

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

            st = DAO.connection.createStatement();
            String filePath = new File("").getAbsolutePath();
            String extension = "/src/test/scripts/create_tables_and_populate.sql";
            filePath += extension;
            String createDbQuery = FileToString.read(Paths.get(filePath));

            st = DAO.connection.createStatement();
            st.executeUpdate(createDbQuery);
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
        MyBank bank = null;
        Assert.assertNull(bp.get("345"));
        bp.persist("345", "Hvidvasken");
        Assert.assertEquals(bp.get("345").getName(), "Hvidvasken");
        Assert.assertEquals(bp.get("345").getCvr(), "345");
    }

    @Test
    public void updateTest() {
        bp.persist("100", "Vaskeriet");
        Bank bank = bp.get("100");
        Assert.assertEquals(bank.getName(), "Vaskeriet");
        bp.update("100","200","Vandmøllen");
        bank = bp.get("200");
        Assert.assertEquals(bank.getName(), "Vandmøllen");
    }

    @Test
    public void deleteTest() {
        bp.persist("200", "Vandmøllen");
        Bank bank = bp.get("200");
        Assert.assertEquals(bank.getName(), "Vandmøllen");
        bp.delete("200");
        bank = bp.get("100");
        Assert.assertNull(bank);
    }

}
