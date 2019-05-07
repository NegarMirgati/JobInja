package ContentProviders;

import DataLayer.DataMappers.Project.BidMapper;
import DataLayer.DataMappers.Project.ProjectMapper;
import DataLayer.DataMappers.user.UserMapper;
import Exceptions.BidAlreadyDoneException;
import Exceptions.ProjectAccessForbiddenException;
import Exceptions.ProjectNotFoundException;
import Entities.*;
import Exceptions.UserNotFoundException;
import Repositories.UserRepo;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;


public class ProjectContentProvider {
//    public static JSONObject getHTMLContentsForProject(String userID, String projectID) throws ProjectNotFoundException {
//        System.out.println("here1");
//        System.out.println("projectId: "+ projectID);
//        Project p = ProjectRepo.getProjectById(projectID);
//        System.out.println("here2");
//        return getProjectContent(p);
//
//    }

    public static JSONObject getProjectContent(Project p) throws ProjectNotFoundException, IOException, SQLException {
        BidMapper bm = new BidMapper();
        JSONObject instance = new JSONObject();
        instance.put("id", p.getId());
        instance.put("title", p.getTitle());
        instance.put("description", p.getDescription());
        instance.put("imageURL" , p.getImageURL());
        instance.put("skills", getProjectSkills(p.getId(), p));
        //instance.put("bids", getProjectBids(p.getId()));
        instance.put("budget", Integer.toString(p.getBudget()));
        instance.put("deadline", Long.toString(p.getDeadline()));
        instance.put("creationDate", Long.toString(p.getDeadline()));
        boolean hasbade = bm.hasBade(p.getId(),"1");
        System.out.println("hasaasasa");
        System.out.println(hasbade);
        instance.put("hasBade", hasbade);
        return instance;

    }

    public static JSONArray getProjectSkills(String pID, Project p) throws ProjectNotFoundException {
        System.out.println("here3");
       // Project p = ProjectRepo.getProjectById(pID);
        System.out.println("here4");
        JSONArray content = new JSONArray();
        JSONObject instance;
        HashMap<String, Skill> skills = new HashMap<>(p.getSkills());
        for (HashMap.Entry<String, Skill> entry : skills.entrySet()) {
            String skillName = entry.getValue().getName();
            String skillPoint = Integer.toString(entry.getValue().getPoint());
            instance = new JSONObject();
            instance.put(skillName, skillPoint);
            content.put(instance);
        }
        return content;
    }

//    public static JSONArray getProjectBids(String pID) throws ProjectNotFoundException {
//        Project p = ProjectRepo.getProjectById(pID);
//        JSONArray content = new JSONArray();
//        JSONObject instance;
//        HashMap<String, Bid> bids = new HashMap<>(p.getBids());
//        for (HashMap.Entry<String, Bid> entry : bids.entrySet()) {
//            String userName = entry.getValue().getBiddingUser();
//            String amount = Integer.toString(entry.getValue().getBiddingAmount());
//            instance = new JSONObject();
//            instance.put(userName, amount);
//            content.put(instance);
//        }
//        return content;
//    }
//    public static boolean hasBadeForProject(String username, String projectID) throws ProjectNotFoundException ,BidAlreadyDoneException {
//        Project p = ProjectRepo.getProjectById(projectID);
//        if ( p.hasBid(username)  == true) {
//            throw  new BidAlreadyDoneException("biding already done");
//        }
//        return p.hasBid(username);
//    }

//        public static JSONArray getHTMLContentsForAllProjects(String userID){
//            JSONArray allProjects = new JSONArray();
//            JSONObject instance;
//            HashMap<String,Project>ProjectList = new HashMap<>(ProjectRepo.getAllProjects());
//            try {
//                User u = UserRepo.findItemInUserList(userID);
//                for (HashMap.Entry<String, Project> entry : ProjectList.entrySet()) {
//                    String projectID = entry.getKey();
//                    Project p = entry.getValue();
//                    if (u.hasRequiredSkills(p.getSkills())) {
//                        instance = new JSONObject();
//                        instance.put(projectID, getProjectContent(p));
//                        allProjects.put(instance);
//                    }
//                }
//
//            }catch (UserNotFoundException e){
//                e.printStackTrace();
//            } catch (ProjectNotFoundException e) {
//                e.printStackTrace();
//            }
//            return allProjects;
//        }
//
    public static void checkAccess(String Uid, String Pid) throws ProjectNotFoundException, ProjectAccessForbiddenException, IOException, SQLException {
        Project p = new ProjectMapper(false).find(Pid);
        User u = new UserMapper().find(Uid);
        if (u.hasRequiredSkills(p.getSkills())) {
            return;
        } else {
            throw new ProjectAccessForbiddenException("Access Forbidden");
        }
    }

}
