package ContentProviders;

import Exceptions.ProjectAccessForbiddenException;
import Exceptions.ProjectNotFoundException;
import Entities.*;
import Exceptions.UserNotFoundException;
import Repositories.*;
import org.json.JSONArray;
import org.json.JSONObject;



import java.util.HashMap;


public class projectContentProvider {
    public static JSONObject getHTMLContentsForProject(String userID, String projectID) throws ProjectNotFoundException {
        Project p = ProjectRepo.getProjectById(projectID);
        return getProjectContent(p);

    }

    private static JSONObject getProjectContent(Project p){

        JSONObject instance = new JSONObject();
        instance.put("id", p.getId());
        instance.put("title", p.getDescription());
        instance.put("imageURL", p.getImageURL());
        instance.put("budget", Integer.toString(p.getBudget()));
        return instance;

    }

    public static boolean hasBadeForProject(String username, String projectID) throws ProjectNotFoundException {
        Project p = ProjectRepo.getProjectById(projectID);
        return p.hasBid(username);
    }

        public static JSONArray getHTMLContentsForAllProjects(String userID){
            JSONArray allProjects = new JSONArray();
            JSONObject instance;
            HashMap<String,Project>ProjectList = new HashMap<>(ProjectRepo.getAllProjects());
            try {
                User u = UserRepo.findItemInUserList(userID);
                for (HashMap.Entry<String, Project> entry : ProjectList.entrySet()) {
                    String projectID = entry.getKey();
                    Project p = entry.getValue();
                    if (u.hasRequiredSkills(p.getSkills())) {
                        instance = new JSONObject();
                        instance.put(projectID, getProjectContent(p));
                        allProjects.put(instance);
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
