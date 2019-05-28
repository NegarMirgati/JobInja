package DataLayer.DataMappers.Project;

import DataLayer.DBCPDBConnectionPool;
import DataLayer.DataMappers.Mapper;
import Entities.Project;
import Entities.Skill;
import HttpConnection.HttpConnection;
import Parsers.MyJsonParser;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProjectSkillMapper extends Mapper<Skill, String> implements IProjectSkillMapper {

    private static final String COLUMNS = " ProjectId, name, point";


    public ProjectSkillMapper() throws SQLException {
        Connection con = DBCPDBConnectionPool.getConnection();
        Statement st =
                con.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS " + "projectSkill" + " " + "(ProjectId VARCHAR(256)," +
                " name VARCHAR(256), point INTEGER, PRIMARY KEY (ProjectId, name), FOREIGN KEY (name) references skill(name))");

      //  fillTable(con);
        st.close();
        con.close();

    }


    @Override
    protected String getFindStatement() {
        return "SELECT " + COLUMNS +
                " FROM projectSkill" +
                " WHERE ProjectId = ?";
    }

    @Override
    protected Skill convertResultSetToDomainModel(ResultSet rs) throws SQLException {
        //Long id = new Long(rs.getString(1));
       // if (loadedMap.containsKey(id)) return (Skill) loadedMap.get(id);
        return  new Skill(
                rs.getString(2),
                rs.getInt(3)
        );
    }


    public static void addToTable(String tableName,ArrayList<String> attrs,ArrayList<String> values  ) throws SQLException {
        Connection con = DBCPDBConnectionPool.getConnection();
        String sqlCommand = insertCommand(tableName,attrs);
        PreparedStatement prp = con.prepareStatement(sqlCommand);
        for(int j = 1; j <= values.size(); j++)
            prp.setString(j, values.get(j-1));
        prp.executeUpdate();
        prp.close();
        con.close();
    }

    private static String insertCommand(String tableName, ArrayList<String> attributes){
        String sqlCommand = "INSERT IGNORE INTO " + tableName + "(";
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
        attr.add("ProjectId");
        attr.add("name");
        attr.add("point");
        return attr;
    }


    protected HashMap<String, Skill> loadAll(ResultSet rs) throws SQLException{
        HashMap<String, Skill> result = new HashMap<>();
        while(rs.next()) {
            Skill s = convertResultSetToDomainModel(rs);
            result.put(s.getName(), s);
        }
        return result;
    }


    public  HashMap<String, Skill> findProjectSkillsById( String ProjectId) throws SQLException {
        HashMap<String, Skill> retval = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBCPDBConnectionPool.getConnection();
            String query = getFindStatement();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, ProjectId);
            rs = pstmt.executeQuery();
            retval = loadAll(rs);
//            pstmt.close();
//            con.close();
            return retval;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            rs.close();
            pstmt.close();
            con.close();
        }
        return retval;
    }


//
//
//    private static void fillTable(ArrayList<HashMap<String, Skill>> allProjectsSkills) throws IOException, SQLException {
//
//        System.out.println("adding project skills");
////        try {
//        ArrayList<ArrayList<String>> values_list = new ArrayList<ArrayList<String>>(tempPair.getKey());
//        ArrayList<HashMap<String, Skill>> allProjectsSkill = new ArrayList<HashMap<String, Skill>>(tempPair.getValue());
//        ArrayList<String> attrs = createAttribute();
//        // loadedMap
//        //  insert skills
//        for (int i = 0; i < values_list.size(); i++) {
//            System.out.println(values_list.get(i));
//            addToTable(con,"project", attrs, values_list.get(i));
//
//        }
//    }
//    @Override
//    public List<Skill> findWithGPA(float minGPA, float maxGPA) {
//        //todo: implement
//        return null;
//    }



}

