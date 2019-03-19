package ContentProviders;

import Exceptions.ProjectNotFoundException;
import Entities.*;
import Exceptions.UserAccessForbidden;
import Exceptions.UserNotFoundException;
import Repositories.*;
import org.json.JSONArray;
import org.json.JSONObject;



import java.util.HashMap;


public class userContentProvider {
    public static JSONObject getHTMLContentsForUser(String uID) throws UserNotFoundException {
        User u = UserRepo.findItemInUserList(uID);
        return getUserContent(u);
    }

    public static JSONArray getUserSkills(String uID) throws UserNotFoundException {
        User u = UserRepo.findItemInUserList(uID);
        JSONArray content = new JSONArray();
        JSONObject instance;
        HashMap<String, Skill> skills = new HashMap<>(u.getSkills());
        for (HashMap.Entry<String, Skill> entry : skills.entrySet()) {
            String skillName = entry.getValue().getName();
            String skillPoint = Integer.toString(entry.getValue().getPoint());
            instance = new JSONObject();
            instance.put(skillName, skillPoint);
            content.put(instance);
        }
        return content;
    }

    public static JSONArray getHTMLContentsForAllUsers(String currentuserID) {
        HashMap<String, User> allUsers = UserRepo.getUserList();
        JSONArray contentMap = new JSONArray();
        JSONObject instance;
        for (HashMap.Entry<String, User> entry : allUsers.entrySet()) {
            String uid = entry.getKey();
            if (uid != currentuserID) {
                instance = new JSONObject();
                instance.put(uid, getShortUserContent(entry.getValue()));
                contentMap.put(instance);
            }

        }
        return contentMap;
    }

    private static JSONObject getUserContent(User u) throws UserNotFoundException {
        JSONObject contentMap = new JSONObject();
        contentMap.put("id", u.getUsername());
        contentMap.put("first name", u.getFirstName());
        contentMap.put("last name", u.getLastName());
        contentMap.put("jobTitle", u.getJobTitle());
        contentMap.put("bio", u.getBio());
        contentMap.put("skills",getUserSkills(u.getUsername()));
        return contentMap;

    }

    private static JSONObject getShortUserContent(User u) {
        JSONObject contentMap = new JSONObject();
        contentMap.put("id", u.getUsername());
        contentMap.put("name", u.getFirstName() + " " + u.getLastName());
        contentMap.put("jobTitle", u.getJobTitle());
        return contentMap;

    }

    public static JSONArray getExtraSkills(String uId) throws UserNotFoundException {
        JSONArray content = new JSONArray();
        JSONObject instance;
        User u = UserRepo.findItemInUserList(uId);
        HashMap<String, Skill> userSkills = new HashMap<String, Skill>(u.getSkills());
        HashMap<String, Skill> allSkills = new HashMap<String, Skill>(SkillRepo.getSkillList());
        for (HashMap.Entry<String, Skill> entry : allSkills.entrySet()) {
            String name = entry.getKey();
            if (!userSkills.containsKey(name)) {
                instance = new JSONObject();
                instance.put(name, uId);
                content.put(instance);
            }

        }
        return content;
    }

    public static void checkCurrentUser(String id) throws UserAccessForbidden {
        if ( !(id.equals("1"))) {
            throw new UserAccessForbidden("access forbidden");

        }
    }
}
