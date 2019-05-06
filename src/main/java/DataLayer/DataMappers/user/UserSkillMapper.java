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
        st.executeUpdate("CREATE TABLE IF NOT EXISTS " + "userSkills" + " " + "username TEXT, (FOREIGN KEY(username) REFERENCES user(username)," +
                "skillName TEXT, FOREIGNKEY(skillName) REFERENCES skill(name), PRIMARY KEY(username, skillName), point INTEGER)");

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

    @Override
    protected String getFindStatement() {
        return "SELECT " + COLUMNS +
                " FROM userSkills" +
                " WHERE username = ?";
    }

}

