package DataLayer;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/***
 * @see <a href="https://www.baeldung.com/java-connection-pooling">A Simple Guide to Connection Pooling in Java</a>
 ***/
public class DBCPDBConnectionPool {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3333/jobinja?allowPublicKeyRetrieval=true&useSSL=false","root", "");
    }

    private DBCPDBConnectionPool(){ }
}
