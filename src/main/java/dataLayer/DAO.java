package dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {

    public static Connection connection;

    public static void connect (String connectionString) {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(connectionString,"bankuser","secret");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static void open() {
        try {
            connection.beginRequest();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void close() {
        try {
            connection.close();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
