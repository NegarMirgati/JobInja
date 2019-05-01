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

    public static void addToTable(String tableName, ArrayList<String> attrs, ArrayList<String> values){
        String sqlCommand = prepareInitialCommand(tableName, attrs);
        try {
            addValuesToInsert(sqlCommand, values);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static String prepareInitialCommand(String tableName, ArrayList<String> attrs){
        String sqlCommand = "INSERT INTO " + tableName + "(";
        for(String attr: attrs)
            sqlCommand += attr + ",";
        sqlCommand = sqlCommand.substring(0, sqlCommand.length()-1);
        sqlCommand += ") VALUES(";
        for(int i = 0; i < attrs.size(); i++)
            sqlCommand += "?,";
        sqlCommand = sqlCommand.substring(0, sqlCommand.length()-1);
        sqlCommand += ");";
        return sqlCommand;
    }

    private static void addValuesToInsert(String sqlCommand, ArrayList<String> values)throws SQLException{
        PreparedStatement prp = conn.prepareStatement(sqlCommand);
        for(int i = 1; i <= values.size(); i++)
            prp.setString(i, values.get(i-1));
        prp.executeUpdate();
        System.out.println("Insertion done successfully.");
    }


}
