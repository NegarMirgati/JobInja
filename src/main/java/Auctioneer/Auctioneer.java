package Auctioneer;

import DataLayer.DataMappers.Project.BidMapper;
import DataLayer.DataMappers.user.UserMapper;
import Exceptions.UserNotFoundException;
import Repositories.*;
import Entities.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Auctioneer {

    public static User performAuction(Project p ) {
        int bestValue = Integer.MIN_VALUE;
        int currentValue = 0;
        User bestUser = null;
        ArrayList<Bid> projectBids = new ArrayList<>();
        int jobOffer = p.getBudget();
        try {
            BidMapper bm = new BidMapper();
            projectBids =bm.findbyProjectId(p.getId());
            System.out.println("bid size: ");
            System.out.println(projectBids.size());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(projectBids == null)
            return null;
        for (Bid b : projectBids) {
            currentValue = 0;
            //User u = UserRepo.findItemInUserList(b.getBiddingUser());
            UserMapper um = new UserMapper();
            User u = null;
            try {
                u = um.find(b.getBiddingUser());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            currentValue += calculatePartialValue(u, p);
            Integer userOffer = b.getBiddingAmount();
            currentValue += jobOffer - userOffer;

            if (currentValue > bestValue) {
                bestValue = currentValue;
                bestUser = u;
                System.out.println("u.getUsername() of candidates");
                System.out.println(u.getUsername());
            }
        }
        //printWinner(bestUser);
        return bestUser;
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
}