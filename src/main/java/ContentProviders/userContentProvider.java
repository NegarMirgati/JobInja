package ContentProviders;

import Exceptions.InvalidSkillException;
import Entities.*;
import Exceptions.UserAccessForbidden;
import Exceptions.UserNotFoundException;
import Repositories.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import Repositories.UserRepo;



import java.util.HashMap;


public class UserContentProvider {
    public static JSONObject getHTMLContentsForUser(String uID) throws UserNotFoundException {
        User u = UserRepo.getUserById(uID);
        JSONObject contentMap = new JSONObject();
        contentMap.put("id", u.getUsername());
        contentMap.put("name", u.getFirstName());
        contentMap.put("lastname", u.getLastName());
        contentMap.put("jobTitle", u.getJobTitle());
        contentMap.put("bio", u.getBio());
        contentMap.put("proLink", u.getProfilePictureURL());
        contentMap.put("skills",getUserSkills(u.getUsername()));
        contentMap.put("possibleSkills", getUserPossibleSkills(u.getUsername()));
        contentMap.put("endorsedSkills", getUserEndorsedSkills(uID));
        System.out.println(getUserEndorsedSkills(uID));
        return contentMap;
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

    public static JSONArray getUserPossibleSkills(String uID) throws UserNotFoundException {
        User u = UserRepo.findItemInUserList(uID);
        JSONArray content = new JSONArray();
        HashMap<String, Skill> skills = new HashMap<>(u.getSkills());
        HashMap<String, Skill> allSkills = new HashMap<>(SkillRepo.getSkillList());
        for (HashMap.Entry<String, Skill> entry : allSkills.entrySet()) {
            String skillName = entry.getValue().getName();
            if(!(skills.containsKey(skillName)))
                content.put(skillName);
        }
        return content;
    }

    public static JSONArray getHTMLContentsForAllUsers(String currentuserID) {
        HashMap<String, User> allUsers = UserRepo.getUserList();
        System.out.println(allUsers);
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

    private static JSONArray getUserEndorsedSkills(String thatUser){
        JSONArray content = new JSONArray();
        try {
            ArrayList<String> skills = UserRepo.getAllSkillsEndorsedByUser("1", thatUser);
            for (int i  = 0; i < skills.size(); i++) {
                content.put(skills.get(i));
            }
        }catch(UserNotFoundException e){
            e.printStackTrace();
        }
        return content;
    }

    private static JSONObject getShortUserContent(User u) {
        JSONObject contentMap = new JSONObject();
        contentMap.put("id", u.getUsername());
        contentMap.put("name", u.getFirstName() + " " + u.getLastName());
        contentMap.put("jobTitle", u.getJobTitle());
        contentMap.put("proLink", u.getProfilePictureURL());
        return contentMap;

    }

    public static void validateSkill(String skillName) throws InvalidSkillException {
        HashMap<String, Skill> allSkills = new HashMap<String, Skill>(SkillRepo.getSkillList());

        if(!allSkills.containsKey(skillName)){
            throw new InvalidSkillException("Invalid Skill");
        }
    }

    public static void checkCurrentUser(String id) throws UserAccessForbidden {
        if ( !(id.equals("1"))) {
            throw new UserAccessForbidden("access forbidden");

        }
    }
}
