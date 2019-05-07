package Commands;
/*
import Entities.*;
import Exceptions.ProjectNotFoundException;
import Exceptions.UserNotFoundException;
import Repositories.*;


import java.util.HashMap;

public class BidCommand implements Command {
    private String projectID;
    private int biddingAmount;
    private String biddingUser;

    public BidCommand(String projectID, int biddingAmount, String biddingUser){
        this.projectID = projectID;
        this.biddingAmount = biddingAmount;
        this.biddingUser = biddingUser;
    }
    public void execute() {
        Project selectedProject = null;
        try {
            selectedProject = ProjectRepo.getProjectById(this.projectID);
        } catch (ProjectNotFoundException e) {
            e.printStackTrace();
        }
        BidRepo.addBid(this.projectID, this.biddingAmount, this.biddingUser);
        selectedProject.addBid(biddingUser, this.projectID, biddingAmount);
     }

    public boolean bidIsPossible() throws UserNotFoundException {
        Project selectedProject = null;
        try {
            selectedProject = ProjectRepo.getProjectById(this.projectID);
        } catch (ProjectNotFoundException e) {
            e.printStackTrace();
        }
        User selectedUser = UserRepo.findItemInUserList(biddingUser);
        if (selectedProject != null && selectedUser != null && biddingAmount <= selectedProject.getBudget()){
            if (selectedUser.hasRequiredSkills(selectedProject.getSkills())) {
                return true;
            }
        }
        return false;
    }
}
*/