package Commands;
import Entities.*;
import Repositories.*;


import java.util.HashMap;

public class BidCommand implements Command {
    private String projectTitle;
    private int biddingAmount;
    private String biddingUser;

    public BidCommand(String projectTitle, int biddingAmount, String biddingUser){
        this.projectTitle = projectTitle;
        this.biddingAmount = biddingAmount;
        this.biddingUser = biddingUser;
    }
    public void execute() {
        checkCommand();
     }

    private void checkCommand() {
        Project selectedProject =  ProjectRepo.findItemInProjectList(projectTitle);
        User selectedUser = UserRepo.findItemInUserList(biddingUser);

        if (selectedProject != null && selectedUser != null && biddingAmount <= selectedProject.getBudget()){
            if (selectedUser.hasRequiredSkills(selectedProject.getSkills())) {
                BidRepo.addBid(this.projectTitle, this.biddingAmount, this.biddingUser);
            }
        }
    }
}
