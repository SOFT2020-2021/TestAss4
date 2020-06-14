package datalayer;

import dataLayer.DAO;
import dataLayer.entitites.User;
import dataLayer.performers.UserPerformer;
import org.junit.*;
import util.FileToString;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class UserPerformerTest {

    UserPerformer up = new UserPerformer();

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
        Assert.assertNull(up.get("100"));
        up.persist("100", "Christian");
        User user = up.get("100");
        Assert.assertEquals(user.getCprNumber(), "100");
        Assert.assertEquals(user.getName(),"Christian");
    }

    @Test
    public void updateTest() {
        up.persist("100", "Christian");
        User user = up.get("100");
        Assert.assertEquals(user.getName(), "Christian");
        up.update("100","100","Alexander");
        user = up.get("100");
        Assert.assertEquals(user.getName(), "Alexander");
    }

    @Test
    public void deleteTest() {
        up.persist("100", "Christian");
        User user = up.get("100");
        Assert.assertEquals(user.getName(), "Christian");
        up.delete("100");
        user = up.get("100");
        Assert.assertNull(user);
    }

}
