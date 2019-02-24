import Exceptions.ProjectNotFoundException;
import Project.*;
import User.*;
import Repositories.*;


import java.util.HashMap;


public class projectContentProvider {
    public static HashMap<String, String> getHTMLContentsForProject(String userID, String projectID) throws ProjectNotFoundException {
        Project p = ProjectRepo.getProjectById(projectID);
        User u = UserRepo.findItemInUserList(userID);
        return getProjectContentMap(p);

    }

    private static HashMap<String, String> getProjectContentMap(Project p){
        HashMap<String, String> contentMap = new HashMap<>();
        contentMap.put("id", p.getId());
        contentMap.put("title", p.getDescription());
        contentMap.put("imageURL", p.getImageURL());
        contentMap.put("budget", Integer.toString(p.getBudget()));
        return contentMap;

    }

    public static HashMap<String,HashMap<String,String>> getHTMLContentsForAllProjects(String userID){
        HashMap<String,HashMap<String,String>> allProjects = new HashMap<String,HashMap<String,String>>();
        HashMap<String,Project>ProjectList = new HashMap<>(ProjectRepo.getAllProjects());
        User u = UserRepo.findItemInUserList(userID);
        for (HashMap.Entry<String, Project> entry : ProjectList.entrySet()) {
            String projectID = entry.getKey();
            Project p = entry.getValue();
            if (u.hasRequiredSkills(p.getSkills())) {
                allProjects.put(projectID, getProjectContentMap(p));
            }
        }
        return allProjects;
    }
    public static boolean checkAccess(String Uid, String Pid) throws ProjectNotFoundException  {
        Project p = ProjectRepo.getProjectById(Pid);
        User u = UserRepo.findItemInUserList(Uid);
        if (u.hasRequiredSkills(p.getSkills())) {
            return true;
        }
        else{
            return false;
        }
    }

}
