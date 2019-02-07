import java.util.*;

public class JobInja {
    private static ArrayList<Bid> BidList = new ArrayList<Bid>();
    private static ArrayList<Project> ProjectList = new ArrayList<Project>();
    private static ArrayList<User> UserList = new ArrayList<User>();

    public static ArrayList<Bid> getBidList() {
        return BidList;
    }

    public static void addBid(String projectTitle, int biddingAmount, String biddingUser) {
        Bid newBid = new Bid( projectTitle, biddingAmount, biddingUser);
        BidList.add(newBid);
    }

    public static ArrayList<Project> getProjectList() {
        return ProjectList;
    }

    public static void addProject(String title, HashMap<String, Integer> skills, int budget) {
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

    public static User findItemInUserList(String title) {
        for(User p : UserList) {
            if(p.getUsername().equals(title)) {
                return p;
            }
        }
        return null;
    }

    public static ArrayList<User> getUserList() {
        return UserList;
    }

    public static void addUser(String username, HashMap<String, Integer> skills) {
        User newUser = new User(username, skills);
        UserList.add(newUser);
    }
}
