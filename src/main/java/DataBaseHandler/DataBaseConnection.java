package DataBaseHandler;
import java.sql.*;
import java.util.ArrayList;

public class DataBaseConnection {

    static private Connection conn;

    public static void connectToDB() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        String dbURL = "jdbc:sqlite:C:\\D-drive-21015\\UT\\Spring98\\IE\\CA\\CA3 TA Session\\jobKhar\\src\\main\\data\\JobInja.db";
        conn = DriverManager.getConnection(dbURL);
        System.out.println("Connection to database established.");
    }


    public static void executeStatement(String sqlCommand) {
        try{
            Statement stm = conn.createStatement();
            stm.execute(sqlCommand);
            System.out.println("Command run successfully.");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
