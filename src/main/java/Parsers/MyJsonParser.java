package Parsers;
import Entities.Skill;
import com.google.gson.*;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.*;

public class MyJsonParser {
    private static JsonParser parser = new JsonParser();
//
//    public static Command parseBidInfo(String json){
//        JsonElement jsonTree = parser.parse(json);
//        if(jsonTree.isJsonObject()) {
//            JsonObject jsonObject = jsonTree.getAsJsonObject();
//            String projectTitle = jsonObject.get("projectTitle").getAsString();
//            String biddingAmount = jsonObject.get("bidAmount").getAsString();
//            String  biddingUser = jsonObject.get("biddingUser").getAsString();
//            Command bid = new BidCommand(projectTitle, Integer.valueOf(biddingAmount), biddingUser);
//            return bid;
//        }
//        else{
//            System.out.println("ERR : incorrect input format.");
//            return null;
//        }
//    }
//
//    public static Command parseUserInfo(String json){
//
//        JsonElement jsonTree = parser.parse(json);
//        if (jsonTree.isJsonObject()) {
//            JsonObject jsonObject = jsonTree.getAsJsonObject();
//            String userName = jsonObject.get("username").getAsString();
//            JsonElement skills = jsonObject.get("skills");
//            HashMap<String, Skill> skillsMap = parseSkills(skills);
//            Command registerCommand  = new RegisterCommand(userName, skillsMap);
//            return registerCommand;
//
//        } else {
//            System.out.println("ERR : incorrect input format.");
//            return null;
//
//        }
//
//    }
//
//    public static Command parseAuctionInfo(String json){
//        JsonElement jsonTree = parser.parse(json);
//        if(jsonTree.isJsonObject()) {
//            JsonObject jsonObject = jsonTree.getAsJsonObject();
//            String projectTitle = jsonObject.get("projectTitle").getAsString();
//            Command auction = new AuctionCommand(projectTitle);
//            return auction;
//        }
//        else{
//            System.out.println("ERR : incorrect input format.");
//            return null;
//        }
//    }
//
    public static Pair<ArrayList<ArrayList<String>>, ArrayList< ArrayList<ArrayList<String>>>> parseProjectList (ArrayList<JsonElement> list) {
        ArrayList<ArrayList<String>> allProjectsData = new ArrayList<ArrayList<String>>();
        ArrayList< ArrayList<ArrayList<String>>> allProjectsSkill = new ArrayList< ArrayList<ArrayList<String>>>();
        for (JsonElement temp : list) {
            Pair<ArrayList<String>, ArrayList<ArrayList<String>>> tempPair = parseProjectInfo(temp.toString());
            allProjectsData.add(tempPair.getKey());
            allProjectsSkill.add(tempPair.getValue());
        }
        return new Pair<>(allProjectsData,allProjectsSkill );
    }
    public static Pair<ArrayList<String>, ArrayList<ArrayList<String>>> parseProjectInfo(String json) {
        ArrayList<String> data = new ArrayList<>();
        JsonElement jsonTree = parser.parse(json);
        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            String id = jsonObject.get("id").getAsString();
            data.add(id);
            String title = jsonObject.get("title").getAsString();
            data.add(title);
            String description = jsonObject.get("description").getAsString();
            data.add(description);
            String imageURL  = jsonObject.get("imageUrl").getAsString();
            data.add(imageURL);
            int budget = jsonObject.get("budget").getAsInt();
            data.add(String.valueOf(budget));
            long deadline = jsonObject.get("deadline").getAsLong();
            data.add(String.valueOf(deadline));
            long creationDate = jsonObject.get("creationDate").getAsLong();
            data.add(String.valueOf(creationDate));
            JsonElement skills = jsonObject.get("skills");
            ArrayList<ArrayList<String>> skillsMap = parseSkills(id, skills);
            //Command addProjectCommand = new AddProjectCommand(id, title, description, imageURL, budget, deadline, skillsMap);
            //return addProjectCommand;
            return new Pair<ArrayList<String>, ArrayList<ArrayList<String>>>(data,skillsMap);

        } else {
           System.out.println("ERR : incorrect input format.");
           return null;
        }
    }

    private static  ArrayList<ArrayList<String>> parseSkills(String id, JsonElement skills){
        JsonArray array = skills.getAsJsonArray();
        HashMap<String, Skill> skillsMap = new HashMap<String, Skill>();
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(id);
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
                       // data.add(skillName);
                       // data.add(String.valueOf(f.getValue().getAsInt()));
                        currentKey = skillName;
                    }
                    else if (key.equals("point")) {
                        Skill s = new Skill(currentKey, f.getValue().getAsInt());
                        skillsMap.put(currentKey, s);
                        temp.add(currentKey);
                        temp.add(String.valueOf(f.getValue().getAsInt()));
                    }
                }
            }
            data.add(temp);
        }
       return data;
    }

    public static ArrayList<String> parseSkillList (ArrayList<JsonElement> list) {
        ArrayList<String > c = new ArrayList<String>();
        for (JsonElement temp : list) {
            c.add(parseSkillInfo(temp.toString()));
        }
        return c;
    }
    public static String parseSkillInfo(String json) {
        JsonElement jsonTree = parser.parse(json);
        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            String name = jsonObject.get("name").getAsString();
            //int point = jsonObject.get("point").getAsInt();
            //Command addSkillCommand = new AddSkillCommand(name,-1)
            return name;

        } else {
            System.out.println("ERR : incorrect input format.");
            return null;
        }
    }




}
