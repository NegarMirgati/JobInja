import Exceptions.ProjectNotFoundException;
import Project.*;
import User.*;
import Repositories.*;


import java.util.HashMap;


public class projectContentProvider {
    public static HashMap<String, String> getHTMLContentsForProject(String userID, String projectID) throws ProjectNotFoundException {
        Project p = ProjectRepo.getProjectById(projectID);
        User u = UserRepo.findItemInUserList(userID);
        if (u.hasRequiredSkills(p.getSkills())) {
            //System.out.println("heeeeeere " + getProjectContentMap(p));
            return getProjectContentMap(p);
        }
        else{
            return null;
        }
    }

    private static HashMap<String, String> getProjectContentMap(Project p){
        HashMap<String, String> contentMap = new HashMap<>();
        contentMap.put("id", p.getId());
        contentMap.put("title", p.getDescription());
        contentMap.put("imageURL", p.getImageURL());
        contentMap.put("budget", Integer.toString(p.getBudget()));
        return contentMap;

    }
}
