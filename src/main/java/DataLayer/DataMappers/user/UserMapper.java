package DataLayer.DataMappers.user;
import Entities.Skill;
import Entities.User;
import DataLayer.DataMappers.Mapper;
import java.sql.*;
import DataLayer.*;
import java.util.*;

public class UserMapper extends Mapper<User, String> implements IUserMapper{

    private static final String COLUMNS = "username, firstName, lastName, jobTitle, profilePictureURL, bio";

    public UserMapper() throws SQLException {
        Connection con = DBCPDBConnectionPool.getConnection();
        Statement st =
                con.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS " + "user" + " " + "(username TEXT PRIMARY KEY, firstName TEXT," +
                " lastName TEXT, jobTitle TEXT, profilePictureURL TEXT, bio TEXT)");

        st.close();
        con.close();
    }

    @Override
    protected User convertResultSetToDomainModel(ResultSet rs) throws SQLException{
        UserSkillMapper u = new UserSkillMapper();

        return new User(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        u.findUserSkillsById(rs.getString(1)),
                        rs.getString(6)
                );
    }

    @Override
    protected String getFindStatement() {
        return "SELECT " + COLUMNS +
                " FROM user" +
                " WHERE username = ?";
    }

}
