package DataBaseHandler;

public class SkillsHandler {
    public static void createSkillsTable(){
        String sqlCommand = "CREATE TABLE IF NOT EXISTS skills (\n" +
                "id TEXT PRIMARY KEY, \n" +
                "name TEXT, \n" +
                "point INTEGER \n" +
                ");";
        DataBaseConnection.executeStatement(sqlCommand);
    }

}
