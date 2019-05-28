package DataLayer;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/***
 * @see <a href="https://www.baeldung.com/java-connection-pooling">A Simple Guide to Connection Pooling in Java</a>
 ***/
public class DBCPDBConnectionPool {

    private static BasicDataSource ds = new BasicDataSource();
    private final static String dbURL = "jdbc:mysql://localhost:3306/var/lib/newDB.db";

    static {
        ds.setUrl(dbURL);
        ds.setMinIdle(1);
        ds.setMaxIdle(4);
        ds.setUsername("root");
        ds.setPassword("password");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3303/jobinja","root", "");
    }

    private DBCPDBConnectionPool(){ }
}
