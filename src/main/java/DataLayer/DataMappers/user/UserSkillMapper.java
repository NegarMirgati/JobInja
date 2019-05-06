package DataLayer.DataMappers.user;
import Entities.Skill;
import Entities.User;
import DataLayer.DataMappers.Mapper;
import java.sql.*;
import DataLayer.*;
import java.util.*;

public class UserSkillMapper extends Mapper<Skill, String> implements IUserSkillMapper{

    private static final String COLUMNS = "username, skillName, point";

    public UserSkillMapper() throws SQLException {
        Connection con = DBCPDBConnectionPool.getConnection();
        Statement st = con.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS " + "userSkills" + " " + "(username TEXT," +
                " skillName TEXT, point INTEGER, PRIMARY KEY (username, skillName), FOREIGN KEY (skillName) references skill(name), FOREIGN KEY (username) references user(username))");

        st.close();
        con.close();
    }

    @Override
    protected Skill convertResultSetToDomainModel(ResultSet rs) throws SQLException{
        return new Skill(rs.getString(2), rs.getInt(3));
    }


    protected HashMap<String, Skill> loadAll(ResultSet rs) throws SQLException{
        HashMap<String, Skill> result = new HashMap<>();
        while(rs.next()) {
            Skill s = convertResultSetToDomainModel(rs);
            result.put(s.getName(), s);
        }
        return result;
    }

    public HashMap<String, Skill> findUserSkillsById(String userId){
        ResultSet rs = null;
        try{
            Connection con = DBCPDBConnectionPool.getConnection();
            String query = getFindStatement();
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            return loadAll(rs);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void addToTable(Connection con,String tableName,ArrayList<String> attrs,ArrayList<String> values) throws SQLException {
        String sqlCommand = insertCommand(tableName,attrs);
        PreparedStatement prp = con.prepareStatement(sqlCommand);
        for(int j = 1; j <= values.size(); j++)
            prp.setString(j, values.get(j-1));
        prp.executeUpdate();
        prp.close();
    }

    public static ArrayList<String> createAttribute() {
        ArrayList<String>attr = new ArrayList<>();
        attr.add("username");
        attr.add("skillName");
        attr.add("point");
        return attr;
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

    @Override
    protected String getFindStatement() {
        return "SELECT " + COLUMNS +
                " FROM userSkills" +
                " WHERE username = ?";
    }

}

