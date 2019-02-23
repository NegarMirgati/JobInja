import Exceptions.ProjectNotFoundException;
import Project.*;
import User.*;
import Repositories.*;


import java.util.HashMap;


public class userContentProvider {
    public static HashMap<String, String> getHTMLContentsForUser(String userID, String uID) throws ProjectNotFoundException {
        User u = UserRepo.getUserById(uID);
        if (u != null) {
            return getUserContentMap(u);
        }
        else{
            return null;
        }
    }

    private static HashMap<String, String> getUserContentMap(User u){
        HashMap<String, String> contentMap = new HashMap<>();
        contentMap.put("id", u.getUsername());
        contentMap.put("first name", u.getFirstName());
        contentMap.put("last name", u.getLastName());
        contentMap.put("jobTitle", u.getJobTitle());
        contentMap.put("bio", u.getBio());
        return contentMap;

    }
}
