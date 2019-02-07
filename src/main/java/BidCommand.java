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
            if ( checkSkills( selectedProject.getSkills(),selectedUser.getSkills() )== true) {
                JobInja.addBid(this.projectTitle, this.biddingAmount, this.biddingUser);
            }
            else {
                System.out.println("BidCommand not executed (inadequate skills)");
            }
        }
        else {
            System.out.println("BidCommand not executed");
        }
    }

    private boolean checkSkills(HashMap<String, Integer> PSkills , HashMap<String, Integer> USkills) {
        for (String name: PSkills.keySet()) {

            if (USkills.containsKey(name)) {

                if (USkills.get(name) <= PSkills.get(name)) {
                   return false;
                }
            } else {
                return true;
            }
        }
        return true;
    }

}
