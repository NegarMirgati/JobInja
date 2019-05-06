package DataLayer.DataMappers.Skill;

import DataLayer.DBCPDBConnectionPool;
import DataLayer.DataMappers.Mapper;
import Entities.Skill;
import HttpConnection.HttpConnection;
import Parsers.MyJsonParser;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;


public class SkillMapper extends Mapper<Skill, Integer> implements ISkillMapper {

    private static final String COLUMNS = "name";

    public void initialize() throws SQLException{
        Connection con = DBCPDBConnectionPool.getConnection();
        Statement st = con.createStatement();
        st.executeUpdate("CREATE TABLE IF NOT EXISTS " + "skill" + " " + "(name TEXT PRIMARY KEY)");
        try {
            fillTable(con);
        } catch (SQLException| IOException e ){
            // Logger.getLogger(SQLite.class.getName()).log(Level.SEVERE, null, ex);
        }
        st.close();
        con.close();

    }

    private static void fillTable(Connection con) throws IOException, SQLException {

        System.out.println("adding skills");
        HttpConnection connection = new HttpConnection();
//        try {
        ArrayList<JsonElement> skillList = connection.httpGet(new URL("http://142.93.134.194:8000/joboonja/skill"));
        ArrayList<String> values_list = MyJsonParser.parseSkillList(skillList);
        ArrayList<String> attrs = createAttribute();
   // loadedMap

        for (int i = 0; i < values_list.size(); i++) {
            ArrayList<String> values = new ArrayList<String>();
            values.add(values_list.get(i));
            addToTable(con,"skill", attrs, values);

        }
    }


    @Override
    protected String getFindStatement() {
        return "SELECT " + COLUMNS +
                " FROM skill" +
                " WHERE name = ?";
    }


    public boolean hasSkill(String skillName) {
        try {
            Connection con = DBCPDBConnectionPool.getConnection();
            String sqlCommand = "SELECT " + "COUNT(*)" +
                    " FROM skill" +
                    " WHERE name = ?";

            PreparedStatement prp = con.prepareStatement(sqlCommand);
            prp.setString(1, skillName);
            ResultSet rs = prp.executeQuery();
            prp.close();
            con.close();
            int count = covertCountResultToInt(rs);
            if(count > 0)
                return true;
            else
                return false;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;

    }

    protected int covertCountResultToInt(ResultSet rs){
        try {
            while (rs.next()) {
                String count = rs.getString(1);
                return Integer.valueOf(count);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    protected Skill convertResultSetToDomainModel(ResultSet rs) throws SQLException {
        String id = new String(rs.getString(1));
        if (loadedMap.containsKey(id)) return (Skill) loadedMap.get(id);
        return  new Skill(
                rs.getString(1),
                -1
        );
    }

    protected ArrayList<Skill> loadAll(ResultSet rs) throws SQLException{
        ArrayList<Skill> result = new ArrayList<>();
        while(rs.next()) {
            Skill s = convertResultSetToDomainModel(rs);
            result.add(s);
        }
        return result;
    }

    private static String findPossibleSkillsCommand(String tableName, int numOfExistingSkills){
        String sqlCommand = "SELECT name FROM " + tableName + " WHERE name NOT IN (" ;
        String substring = "";
        for(int i = 0; i < numOfExistingSkills; i++){
            if(i != numOfExistingSkills -1)
                substring += "?,";
            else
                substring += "?";
        }
        sqlCommand += substring + ");";
        return sqlCommand;
    }

    public  ArrayList<Skill> getPossibleSkills(ArrayList<String> values){
        ResultSet rs = null;
        try{
            Connection con = DBCPDBConnectionPool.getConnection();
            String sqlCommand = findPossibleSkillsCommand("skill", values.size());
            PreparedStatement prp = con.prepareStatement(sqlCommand);
            for(int i = 0; i < values.size(); i++){
                prp.setString(i + 1, values.get(i));
            }
            rs = prp.executeQuery();
            ArrayList<Skill> retval = loadAll(rs);
            prp.close();
            con.close();
            return retval;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
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
        ArrayList<String> attrs = new ArrayList<String>();
        attrs.add("name");
        return attrs;
    }

    public static void addToTable(Connection con,String tableName,ArrayList<String> attrs,ArrayList<String> values  ) throws SQLException {
        String sqlCommand = insertCommand(tableName,attrs);
        PreparedStatement prp = con.prepareStatement(sqlCommand);
        for(int j = 1; j <= values.size(); j++)
            prp.setString(j, values.get(j-1));
        prp.executeUpdate();
        prp.close();
    }

    }

