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

    public static void addToTable(String tableName, ArrayList<String> attributes, ArrayList<String> values){
        String sqlCommand = insertCommand(tableName, attributes);
        try {
            PreparedStatement prp = conn.prepareStatement(sqlCommand);
            for(int i = 1; i <= values.size(); i++)
                prp.setString(i, values.get(i-1));
            prp.executeUpdate();
            System.out.println("Insertion done successfully.");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static String insertCommand(String tableName, ArrayList<String> attributes){
        String sqlCommand = "INSERT INTO " + tableName + "(";
        for(String attr: attributes)
            sqlCommand += attr + ",";
        sqlCommand = sqlCommand.substring(0, sqlCommand.length()-1);
        sqlCommand += ") VALUES(";
        for(int i = 0; i < attributes.size(); i++)
            sqlCommand += "?,";
        sqlCommand = sqlCommand.substring(0, sqlCommand.length()-1);
        sqlCommand += ");";
        return sqlCommand;
    }



}
