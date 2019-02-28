package Repositories;
import Commands.Command;
import Parsers.*;
import HttpConnection.*;
import Entities.*;
import Exceptions.*;
import com.google.gson.JsonElement;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.*;

public class ProjectRepo {
    private static ProjectRepo ourInstance = new ProjectRepo();
    private static HashMap<String, Project> projectList = new HashMap<String, Project>();

    public static ProjectRepo getInstance() {
        return ourInstance;
    }

    private ProjectRepo() {
    }

    public static void addProject(String id, String title, String description, String imageURL, int budget, long deadline, HashMap<String, Skill> skills) {
        Project newProject = new Project(id, title, description, imageURL, budget, deadline, skills);
        projectList.put(title, newProject);
    }

    public static Project findItemInProjectList(String title) {
        return projectList.get(title);
    }

    public static Project getProjectById(String id) throws ProjectNotFoundException {
        Iterator it = projectList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Project p = (Project) pair.getValue();
            if(p.getId().equals(id)){
                return p;
            }
        }
        throw new ProjectNotFoundException("Project Not Found");
    }
    public static HashMap<String,Project> getAllProjects(){
        return new HashMap<>(projectList);
    }

    public static void addProjects(){
        System.out.println("adding rpojects");
        HttpConnection connection = new HttpConnection();
        try {
            ArrayList<JsonElement> projectlist =  connection.httpGet(new URL("http://142.93.134.194:8000/joboonja/project"));
            ArrayList<Command> command_list = MyJsonParser.parseProjectList(projectlist);
            for (Command command : command_list) {
                command.execute();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
