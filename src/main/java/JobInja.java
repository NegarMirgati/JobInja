import java.util.*;

public class JobInja {
    private static HashMap<String, ArrayList<Bid>> bidList = new HashMap<String, ArrayList<Bid>>();
    private static HashMap<String, Project> projectList = new HashMap<String, Project>();
    private static HashMap<String, User> userList = new HashMap<String, User>();

    public static HashMap<String, ArrayList<Bid>> getBidList() {
        return bidList;
    }

    public static void addBid(String projectTitle, int biddingAmount, String biddingUser) {
        Bid newBid = new Bid( projectTitle, biddingAmount, biddingUser);
        if(!bidList.containsKey(projectTitle))
            bidList.put(projectTitle, new ArrayList<Bid>());
        bidList.get(projectTitle).add(newBid);
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

    public static User findItemInUserList(String username) {
        return userList.get(username);
    }

    public static HashMap<String, User> getUserList() {
        return userList;
    }

    public static void addUser(String username, HashMap<String, Skill> skills) {
        User newUser = new User(username, skills);
        userList.put(username, newUser);
    }

    public static void performAuction(String projectTitle) {
        int bestValue = Integer.MIN_VALUE;
        int currentValue = 0;
        String bestUser = "";
        Project p = projectList.get(projectTitle);
        int jobOffer = p.getBudget();
        ArrayList<Bid> projectBids = bidList.get(projectTitle);
        if(projectBids == null)
            return;
        for (Bid b : projectBids) {
            currentValue = 0;
            User u = findItemInUserList(b.getBiddingUser());
            currentValue += calculatePartialValue(u, p);
            Integer userOffer = b.getBiddingAmount();
            currentValue += jobOffer - userOffer;

            if (currentValue > bestValue) {
                bestValue = currentValue;
                bestUser = b.getBiddingUser();
            }
        }
        printWinner(bestUser);
    }

    public static int calculatePartialValue(User u, Project p){
        HashMap<String, Skill> userSkills = u.getSkills();
        HashMap<String, Skill> projectSkills = p.getSkills();
        int currentValue = 0;
        for (Map.Entry<String, Skill> entry : projectSkills.entrySet()) {
            String key = entry.getKey();
            Integer jobSkill = entry.getValue().getPoint();
            Integer userSkill = userSkills.get(key).getPoint();
            currentValue += 10000 * Math.pow(userSkill - jobSkill, 2);
        }
        return currentValue;
    }

    public static void printWinner(String username){
        System .out.println("-> winner: " + username);
    }
/*
    public static void printProjects() {
        for (Map.Entry<String, Project> entry : projectList.entrySet()) {
            String key = entry.getKey();
            Project p = entry.getValue();
            System.out.println("key: " + key + "  id:" + p.getId() + "  title: " + p.getTitle() );
        }
    } */
}
