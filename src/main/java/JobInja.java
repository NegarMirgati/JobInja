import java.util.*;

public class JobInja {
    private ArrayList<Bid> BidList = new ArrayList<Bid>();
    private static ArrayList<Project> ProjectList = new ArrayList<Project>();
    private ArrayList<User> UserList = new ArrayList<User>();

    public ArrayList<Bid> getBidList() {
        return BidList;
    }

    public void addBid(String projectTitle, int biddingAmount, String biddingUser) {
        Bid newBid = new Bid();
        newBid.setProjectTitle(projectTitle);
        newBid.setBiddingAmount(biddingAmount);
        newBid.setBiddingUser(biddingUser);
        BidList.add(newBid);
    }

    public ArrayList<Project> getProjectList() {
        return ProjectList;
    }

    public void addProject(String title, HashMap<String, Integer> skills, int budget) {
        Project newProject = new Project();
        newProject.setTitle(title);
        newProject.setBudget(budget);
        for (HashMap.Entry<String, Integer> entry : skills.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            newProject.addSkill(key, value);
        }
        ProjectList.add(newProject);
    }

    public static Project findItemInProjectList(String title) {
        for(Project p : ProjectList) {
            if(p.getTitle().equals(title)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<User> getUserList() {
        return UserList;
    }

    public void addUser(String username, HashMap<String, Integer> skills) {
        User newUser = new User();
        newUser.setUsername(username);
        for (HashMap.Entry<String, Integer> entry : skills.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            newUser.addSkill(key, value);
        }
        UserList.add(newUser);
    }
}
