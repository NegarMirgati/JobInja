package ContentProviders;

import Exceptions.ProjectAccessForbiddenException;
import Exceptions.ProjectNotFoundException;
import Entities.*;
import Exceptions.UserNotFoundException;
import Repositories.*;


import java.util.HashMap;


public class projectContentProvider {
    public static HashMap<String, String> getHTMLContentsForProject(String userID, String projectID) throws ProjectNotFoundException {
        Project p = ProjectRepo.getProjectById(projectID);
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

    public static boolean hasBadeForProject(String username, String projectID) throws ProjectNotFoundException {
        Project p = ProjectRepo.getProjectById(projectID);
        return p.hasBid(username);
    }

    public static HashMap<String,HashMap<String,String>> getHTMLContentsForAllProjects(String userID){
        HashMap<String,HashMap<String,String>> allProjects = new HashMap<String,HashMap<String,String>>();
        HashMap<String,Project>ProjectList = new HashMap<>(ProjectRepo.getAllProjects());
        try {
            User u = UserRepo.findItemInUserList(userID);
            for (HashMap.Entry<String, Project> entry : ProjectList.entrySet()) {
                String projectID = entry.getKey();
                Project p = entry.getValue();
                if (u.hasRequiredSkills(p.getSkills())) {
                    allProjects.put(projectID, getProjectContentMap(p));
                }
            }
        }catch (UserNotFoundException e){
            e.printStackTrace();
        }
        return allProjects;
    }
    public static void checkAccess(String Uid, String Pid) throws ProjectNotFoundException, ProjectAccessForbiddenException {
        Project p = ProjectRepo.getProjectById(Pid);
        try {
            User u = UserRepo.findItemInUserList(Uid);
            if (u.hasRequiredSkills(p.getSkills())) {
                return;
            } else {
                throw new ProjectNotFoundException("Access Forbidden");
            }
        }catch (UserNotFoundException e){
            e.printStackTrace();
        }
    }

}
