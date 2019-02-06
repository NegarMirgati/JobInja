
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
        if (selectedProject != null){
            // continue
        }
    }

}
