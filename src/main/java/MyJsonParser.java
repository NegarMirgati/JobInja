import com.google.gson.*;
import java.util.*;

public class MyJsonParser {
    private static JsonParser parser = new JsonParser();

    public static Command parseBidInfo(String json){
        JsonElement jsonTree = parser.parse(json);
        if(jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            String projectTitle = jsonObject.get("projectTitle").getAsString();
            String biddingAmount = jsonObject.get("bidAmount").getAsString();
            String  biddingUser = jsonObject.get("biddingUser").getAsString();
            Command bid = new BidCommand(projectTitle, Integer.valueOf(biddingAmount), biddingUser);
            return bid;
        }
        else{
            System.out.println("ERR : incorrect input format.");
            return null;
        }
    }

    public static Command parseUserInfo(String json){

        JsonElement jsonTree = parser.parse(json);
        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            String userName = jsonObject.get("username").getAsString();
            JsonElement skills = jsonObject.get("skills");
            HashMap<String, Skill> skillsMap = parseSkills(skills);
            Command registerCommand  = new RegisterCommand(userName, skillsMap);
            return registerCommand;

        } else {
            System.out.println("ERR : incorrect input format.");
            return null;

        }

    }

    public static Command parseAuctionInfo(String json){
        JsonElement jsonTree = parser.parse(json);
        if(jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            String projectTitle = jsonObject.get("projectTitle").getAsString();
            Command auction = new AuctionCommand(projectTitle);
            return auction;
        }
        else{
            System.out.println("ERR : incorrect input format.");
            return null;
        }
    }

    public static ArrayList<Command> parseProjectList (ArrayList<JsonElement> list) {
        ArrayList<Command> c = new ArrayList<Command>();
        for (JsonElement temp : list) {
            c.add(parseProjectInfo(temp.toString()));

        }
        return c;
    }

    public static Command parseProjectInfo(String json) {
        JsonElement jsonTree = parser.parse(json);
        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            String id = jsonObject.get("id").getAsString();
            String title = jsonObject.get("title").getAsString();
            String description = jsonObject.get("description").getAsString();
            String imageURL  = jsonObject.get("imageUrl").getAsString();
            int budget = jsonObject.get("budget").getAsInt();
            long deadline = jsonObject.get("deadline").getAsInt();
            JsonElement skills = jsonObject.get("skills");
            HashMap<String, Skill> skillsMap = parseSkills(skills);
            Command addProjectCommand = new AddProjectCommand(id, title, description, imageURL, budget, deadline, skillsMap);
            return addProjectCommand;

        } else {
            System.out.println("ERR : incorrect input format.");
            return null;
        }
    }

    private static HashMap<String, Skill> parseSkills(JsonElement skills){
        JsonArray array = skills.getAsJsonArray();
        HashMap<String, Skill> skillsMap = new HashMap<String, Skill>();
        for (int i = 0; i < array.size(); i++) {
            JsonObject reader = array.get(i).getAsJsonObject();
            Set set = reader.entrySet();
            Set<java.util.Map.Entry<String, JsonElement>> keys = reader.entrySet();
            if (keys.isEmpty()) {
                System.out.println("Empty JSON Object");
            }

            else {
                String currentKey = "";
                for (Iterator<java.util.Map.Entry<String, JsonElement>> it = set.iterator(); it.hasNext(); ) {
                    java.util.Map.Entry<String, JsonElement> f = it.next();
                    String key = f.getKey();
                    if (key.equals("name")) {
                        String skillName = f.getValue().getAsString();
                        Skill s = new Skill(skillName, 0);
                        skillsMap.put(skillName, s);
                        currentKey = skillName;
                    }
                    else if (key.equals("points")) {
                        Skill s = new Skill(currentKey, f.getValue().getAsInt());
                        skillsMap.put(currentKey, s);
                    }
                }
            }
        }
    return skillsMap;
    }

}


