import java.util.*;

public class JobInja {
    private static ArrayList<Bid> BidList = new ArrayList<Bid>();
    private static ArrayList<Project> ProjectList = new ArrayList<Project>();
    private static ArrayList<User> UserList = new ArrayList<User>();

    public ArrayList<Bid> getBidList() {
        return BidList;
    }

    public void addBid(String projectTitle, int biddingAmount, String biddingUser) {
        Bid newBid = new Bid( projectTitle, biddingAmount, biddingUser);
        BidList.add(newBid);
    }

    public ArrayList<Project> getProjectList() {
        return ProjectList;
    }

    public void addProject(String title, HashMap<String, Integer> skills, int budget) {
        Project newProject = new Project(title, skills, budget);
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
        User newUser = new User(username, skills);
        UserList.add(newUser);
    }
}
