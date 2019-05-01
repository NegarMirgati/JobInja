package DataBaseHandler;

import HttpConnection.HttpConnection;
import Parsers.MyJsonParser;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SkillsHandler {
    public static void addSkills(){
        createSkillsTable();
        fillTable();
    }

    public static void createSkillsTable(){
        String sqlCommand = "CREATE TABLE IF NOT EXISTS skills (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, \n"+
                "name TEXT, \n" +
                "point INTEGER \n" +
                ");";
        DataBaseConnection.executeStatement(sqlCommand);
    }

    private static void fillTable() {

        System.out.println("adding skills");
        HttpConnection connection = new HttpConnection();
        try {
            ArrayList<JsonElement> skillList = connection.httpGet(new URL("http://142.93.134.194:8000/joboonja/skill"));
            ArrayList<String> values_list = MyJsonParser.parseSkillList(skillList);
            ArrayList<String> attrs = createAttribute();
            for (int i = 0; i < values_list.size(); i++) {
                System.out.println(values_list.get(i));
                ArrayList<String> values = new ArrayList<String>();
                values.add(values_list.get(i));
                DataBaseConnection.addToTable("skills", attrs, values);
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static ArrayList<String> createAttribute(){
        ArrayList<String> attrs = new ArrayList<String>();
        attrs.add("name");
        return attrs;
    }


}
