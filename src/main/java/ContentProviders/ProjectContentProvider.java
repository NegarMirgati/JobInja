package ContentProviders;

import DataLayer.DataMappers.Project.BidMapper;
import DataLayer.DataMappers.Project.ProjectMapper;
import DataLayer.DataMappers.user.UserMapper;
import Exceptions.BidAlreadyDoneException;
import Exceptions.ProjectAccessForbiddenException;
import Exceptions.ProjectNotFoundException;
import Entities.*;
import Exceptions.UserNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


public class ProjectContentProvider {

    public static JSONArray getContentsForAllProjects() throws ProjectNotFoundException, SQLException {
        ArrayList<Project> allProjects = new ArrayList<>();
        try {
            System.out.println("here in project content provider before find");
            ProjectMapper pm = new ProjectMapper(false);
            allProjects = pm.findAllOrderBycreationDate();
            System.out.println("here in project content provider after find");
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONArray contentMap = new JSONArray();
        JSONObject instance;
        for (Project p : allProjects) {
            String id = p.getId();
            instance = new JSONObject();
            try {
                instance.put(id, getProjectContent(p));
            } catch (IOException e) {
                System.out.println(e);
                e.printStackTrace();
            }
            contentMap.put(instance);
        }
        return contentMap;
    }
    public static JSONObject getProjectContent(Project p) throws ProjectNotFoundException, IOException, SQLException {

        BidMapper bm = new BidMapper();
        UserMapper um = new UserMapper();
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
        //System.out.println("here1");
        boolean hasbade = bm.hasBade(p.getId(),"1");
        //System.out.println("here2");
        instance.put("hasBade", hasbade);
        //System.out.println("wiwiwiwinwewee");
        //System.out.println(p.getWinner());
        if ( p.getWinner().equals("NULL") || p.getWinner()=="NULL" ) {
            instance.put("winner", "بدون برنده");
            //System.out.println("p.getWinner().equals(\"NULL\")");
            //System.out.println(p.getWinner());
        }
        else if (p.getWinner().equals("") || p.getWinner() == "" ){
            instance.put("winner", p.getWinner());
            //System.out.println("in winner 0");
        }
        else {
            User u = um.find(p.getWinner());
            if ( u == null){
                instance.put("winner", "");
            }
            else {
                instance.put("winner", u.getFirstName() + " " + u.getLastName());
            }
        }
        return instance;

    }

    public static JSONArray getProjectSkills(String pID, Project p) throws ProjectNotFoundException {
        //System.out.println("here3");
       // Project p = ProjectRepo.getProjectById(pID);
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

    public static JSONArray getSearchedProjects(String query) {
        ArrayList<Project> found = new ArrayList<>();
        try {
            ProjectMapper pm = new ProjectMapper(false);
            found = pm.findbyTitleOrDes(query);
            System.out.println(found.size());
            for (Project p:found){
                System.out.println(p.getBudget());
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        JSONArray contentMap = new JSONArray();
        JSONObject instance;
        for (Project p : found) {
            String id = p.getId();
            instance = new JSONObject();
            try {
                instance.put(id, getProjectContent(p));
                contentMap.put(instance);
            } catch (ProjectNotFoundException | IOException | SQLException e) {
                e.printStackTrace();
            }

        }
        return contentMap;
    }
    public static  ArrayList<Project> getFinishedProjects(){
        ArrayList<Project> found = new ArrayList<>();
        try {
            ProjectMapper pm = new ProjectMapper(false);
            found = pm.findFinishedProjects();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return found;

    }

}
