package entities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
    public static Connection getDatabaseConnection() throws SQLException {

        String protocol = "jdbc";
        String dbServer = "mysql";
        String host = "localhost";
        String port = "3306";
        String dbName = "scratch";

        String dbUrl = String.format("%s:%s://%s:%s/%s",protocol, dbServer, host, port, dbName);
        System.out.println(dbUrl);

        return DriverManager.getConnection(dbUrl, "root", "Theboys2!");

    }
}
