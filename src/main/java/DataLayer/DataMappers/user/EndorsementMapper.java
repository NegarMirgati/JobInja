package DataLayer.DataMappers.user;

import DataLayer.DBCPDBConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EndorsementMapper  {
    private static final String COLUMNS = "endorserId, endorsedId, skillName";

    public EndorsementMapper() throws SQLException {
        Connection con = DBCPDBConnectionPool.getConnection();
        Statement st =
                con.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS " + "endorsement" + " " + "(endorserId TEXT , endorsedId TEXT," +
                " skillName TEXT, FOREIGN KEY endorserId REFERENCES user(username)" +
                ", FOREIGN KEY endorsedId REFERENCES user(username), FOREIGN KEY (skillName) REFERENCES skill(name)"+
        ", PRIMARY KEY(endorserId, endorsedId, skillName))");

        st.close();
        con.close();
    }
}
