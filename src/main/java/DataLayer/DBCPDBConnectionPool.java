package DataLayer;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/***
 * @see <a href="https://www.baeldung.com/java-connection-pooling">A Simple Guide to Connection Pooling in Java</a>
 ***/
public class DBCPDBConnectionPool {

    private static BasicDataSource ds = new BasicDataSource();
    private final static String dbURL = "jdbc:sqlite:/Users/negar/Desktop/CA7/test.db";

    static {
        ds.setUrl(dbURL);
        ds.setMinIdle(1);
        ds.setMaxIdle(4);
        ds.setDriverClassName("org.sqlite.JDBC");
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DBCPDBConnectionPool(){ }
}
