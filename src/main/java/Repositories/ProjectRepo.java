package Repositories;
import Project.*;
import Skill.*;

import java.util.HashMap;

public class ProjectRepo {
    private static ProjectRepo ourInstance = new ProjectRepo();
    private static HashMap<String, Project> projectList = new HashMap<String, Project>();

    public static ProjectRepo getInstance() {
        return ourInstance;
    }

    private ProjectRepo() {
    }

    public static HashMap<String, Project> getProjectList() {
        return projectList;
    }

    public static void addProject(String id, String title, String description, String imageURL, int budget, long deadline, HashMap<String, Skill> skills) {
        Project newProject = new Project(id, title, description, imageURL, budget, deadline, skills);
        projectList.put(title, newProject);
    }

    public static Project findItemInProjectList(String title) {
        return projectList.get(title);
    }


}
