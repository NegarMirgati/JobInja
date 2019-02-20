package Auctioneer;
import Repositories.*;
import Project.*;
import Bid.*;
import Skill.*;
import User.*;
import java.util.*;

public class Auctioneer {

    public static void performAuction(String projectTitle) {
        int bestValue = Integer.MIN_VALUE;
        int currentValue = 0;
        String bestUser = "";
        Project p = ProjectRepo.findItemInProjectList(projectTitle);
        int jobOffer = p.getBudget();
        ArrayList<Bid> projectBids = BidRepo.getBids(projectTitle);
        if(projectBids == null)
            return;
        for (Bid b : projectBids) {
            currentValue = 0;
            User u = UserRepo.findItemInUserList(b.getBiddingUser());
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
    }
    public static void printSkills() {
        for (Map.Entry<String, Skill> entry : skillList.entrySet()) {
            String key = entry.getKey();
            Skill s = entry.getValue();
            System.out.println("key: " + key + "  name:" + s.getName() + "  point: " + s.getPoint() );
     }
    }
    */
}
