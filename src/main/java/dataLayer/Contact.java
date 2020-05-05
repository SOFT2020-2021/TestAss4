package dataLayer;

import java.sql.Connection;

public interface Contact {

    public void open();
    public void close();
    public void connect(String connectionString);
    public Connection getConnection();

}
