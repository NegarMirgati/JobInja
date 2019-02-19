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
        Project selectedProject =  JobInja.findItemInProjectList(projectTitle);
        User selectedUser = JobInja.findItemInUserList(biddingUser);

        if (selectedProject != null && selectedUser != null && biddingAmount <= selectedProject.getBudget()){
            if (hasRequiredSkills( selectedProject.getSkills(),selectedUser.getSkills())) {
                JobInja.addBid(this.projectTitle, this.biddingAmount, this.biddingUser);
            }
        }
    }

    private boolean hasRequiredSkills(HashMap<String, Skill> PSkills , HashMap<String, Skill> USkills) {
        for (String name: PSkills.keySet()) {
            if (USkills.containsKey(name)) {
                if (USkills.get(name).getPoint() < PSkills.get(name).getPoint()) {
                   return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

}
