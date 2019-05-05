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

public class ProjectSkillMapper extends Mapper<Skill, Integer> implements IProjectSkillMapper {

    private static final String COLUMNS = " ProjectId, name, point";


    public ProjectSkillMapper() throws SQLException {
        Connection con = DBCPDBConnectionPool.getConnection();
        Statement st =
                con.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS " + "projectSkill" + " " + "(ProjectId TEXT," +
                " name TEXT, point INTEGER, PRIMARY KEY (ProjectId, name), FOREIGN KEY (name) references skill(name), FOREIGN KEY (ProjectId) references project(id))");

      //  fillTable(con);
        st.close();
        con.close();

    }


    @Override
    protected String getFindStatement() {
        return "SELECT " + COLUMNS +
                " FROM skill" +
                " WHERE id = ?";
    }

    @Override
    protected Skill convertResultSetToDomainModel(ResultSet rs) throws SQLException {
        Long id = new Long(rs.getString(1));
        if (loadedMap.containsKey(id)) return (Skill) loadedMap.get(id);
        return  new Skill(
                rs.getString(2),
                rs.getInt(3)
        );
    }


    public static void addToTable(Connection con,String tableName,ArrayList<String> attrs,ArrayList<String> values  ) throws SQLException {
        String sqlCommand = insertCommand(tableName,attrs);
        PreparedStatement prp = con.prepareStatement(sqlCommand);
        for(int j = 1; j <= values.size(); j++)
            prp.setString(j, values.get(j-1));
        prp.executeUpdate();
        prp.close();
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
        attr.add("ProjectId");
        attr.add("name");
        attr.add("point");
        return attr;
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

