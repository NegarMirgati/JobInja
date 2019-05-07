package DataLayer.DataMappers.Project;

import DataLayer.DBCPDBConnectionPool;
import DataLayer.DataMappers.Mapper;
import Entities.Bid;
import Entities.Project;
import Entities.Skill;
import HttpConnection.HttpConnection;
import Parsers.MyJsonParser;
import com.google.gson.JsonElement;
import javafx.util.Pair;
import org.sqlite.SQLiteJDBCLoader;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BidMapper extends Mapper<Bid, String> implements IBidMapper {

    private static final String COLUMNS = " projectId, userId, amount";



    public BidMapper() throws SQLException, IOException {
        Connection con = DBCPDBConnectionPool.getConnection();
        Statement st =
                con.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS " + "bid" + " " + "(ProjectId TEXT," +
                " userId TEXT, amount INTEGER, PRIMARY KEY (ProjectId, userId), FOREIGN KEY (userId) references user(username), FOREIGN KEY (ProjectId) references project(id))");

        st.close();
        con.close();

    }


    @Override
    protected String getFindStatement() {
        return "SELECT " + COLUMNS +
                " FROM bid" +
                " WHERE id = ?";
    }

    protected String getFindStatementByProjectId() {
        return "SELECT " + COLUMNS +
                " FROM bid" +
                " WHERE projectId = ?";
    }

    protected String getFindStatementByUserId() {
        return "SELECT " + COLUMNS +
                " FROM bid" +
                " WHERE userId = ?";
    }

    public boolean hasBade(String projectId, String userId) throws SQLException {
            // select the number of rows in the table
            Connection conn = null;
            ResultSet rs = null;
            PreparedStatement prp = null;
            int rowCount = -1;
            try {
                 conn = DBCPDBConnectionPool.getConnection();
                String sqlCommand = "SELECT COUNT(*) FROM bid WHERE projectId = ?"+
                        "AND userId = ?";
                prp = conn.prepareStatement(sqlCommand);
                prp.setString(1, projectId);
                prp.setString(2, userId);
                rs = prp.executeQuery();
                // get the number of rows from the result set
                rs.next();
                rowCount = rs.getInt(1);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                rs.close();
                prp.close();
                conn.close();
            }
            return (rowCount > 0) ? true:false;

    }

    public boolean biddingPossible(String projectId, int amount) throws SQLException {
        try {
            ProjectMapper pm = new ProjectMapper(false);
            Project p = pm.find(projectId);
            if (p.getBudget() >= amount){
                return true;
            }
            else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
     return false;

    }



    @Override
    protected Bid convertResultSetToDomainModel(ResultSet rs) throws SQLException {
        String id = (rs.getString(1));
        if (loadedMap.containsKey(id)) return (Bid) loadedMap.get(id);
        return  new Bid(
                rs.getString(1),
                rs.getInt(3),
                rs.getString(2)

        );
    }

    public  void fillTable(String projectId, String UserId, int amount){
        ArrayList<String> attr = createAttribute();
        ArrayList<String>val = new ArrayList<String>();
        val.add(projectId);
        val.add(UserId);
        val.add(String.valueOf(amount));
        try {
            addToTable("bid", attr, val);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static ArrayList<String> createAttribute() {
        ArrayList<String> attrs = new ArrayList<String>();
        attrs.add("projectId");
        attrs.add("userId");
        attrs.add("amount");
        return attrs;
    }

    public static void addToTable(String tableName,ArrayList<String> attrs,ArrayList<String> values  ) throws SQLException {
        Connection conn = DBCPDBConnectionPool.getConnection();
        String sqlCommand = insertCommand(tableName,attrs);
        PreparedStatement prp = conn.prepareStatement(sqlCommand);
        for(int j = 1; j <= values.size(); j++)
            prp.setString(j, values.get(j-1));
        prp.executeUpdate();

        prp.close();
        conn.close();
    }

    private static String insertCommand(String tableName, ArrayList<String> attributes){
        String sqlCommand = "INSERT OR IGNORE INTO " + tableName + "(";
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

