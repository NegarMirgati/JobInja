package DataLayer.DataMappers.user;
import DataLayer.DataMappers.Mapper;
import DataLayer.DBCPDBConnectionPool;
import Entities.Skill;
import Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class EndorsementMapper extends Mapper<String, String> implements IEndorsementMapper{
    private static final String COLUMNS = "endorserId, endorsedId, skillName";

    public void initialize(){
        try {
            Connection con = DBCPDBConnectionPool.getConnection();
            Statement st = con.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS " + "endorsement" + " " + "(endorserId TEXT, endorsedId Text, skillName TEXT," +
                    " FOREIGN KEY(endorserId) REFERENCES user(username), FOREIGN KEY(endorsedId) REFERENCES userSkills(username), FOREIGN KEY(skillName) REFERENCES userSkills(name)"
                    + ",PRIMARY KEY(endorserId, endorsedId, skillName))");

            st.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    protected String convertResultSetToDomainModel(ResultSet rs) throws SQLException{
        return rs.getString(3);
    }


    protected ArrayList<String> loadAll(ResultSet rs) throws SQLException {
        ArrayList<String> result = new ArrayList<>();
        while (rs.next()) {
            String skillName = convertResultSetToDomainModel(rs);
            System.out.println("ssss" + skillName);
            result.add(skillName);
        }
        return result;
    }

    public static void addToTable(ArrayList<String> values) throws SQLException {
        ArrayList<String> attrs = createAttribute();
        Connection con = DBCPDBConnectionPool.getConnection();
        String sqlCommand = insertCommand("endorsement", attrs);
        PreparedStatement prp = con.prepareStatement(sqlCommand);
        for(int j = 0; j < attrs.size(); j++) {
            prp.setString(j + 1, values.get(j));
        }
        prp.executeUpdate();
        prp.close();
        con.close();
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

    public static ArrayList<String> createAttribute() {
        ArrayList<String>attr = new ArrayList<>();
        attr.add("endorserId");
        attr.add("endorsedId");
        attr.add("skillName");
        return attr;
    }

    public  ArrayList<String> getEndorsedSkills(ArrayList<String> values){
        ResultSet rs = null;
        try{
            Connection con = DBCPDBConnectionPool.getConnection();
            String sqlCommand = getFindStatement();
            PreparedStatement prp = con.prepareStatement(sqlCommand);
            for(int i = 0; i < values.size(); i++){
                prp.setString(i + 1, values.get(i));
            }
            rs = prp.executeQuery();
            System.out.println("salam2");
            ArrayList<String> retval = loadAll(rs);
            prp.close();
            con.close();
            return retval;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected String getFindStatement() {
        return "SELECT " + COLUMNS +
                " FROM endorsement" +
                " WHERE endorserId = ? AND endorsedId = ?";
    }
}
