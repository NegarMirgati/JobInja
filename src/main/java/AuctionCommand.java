public class AuctionCommand implements Command {
    private String projectTitle;

    public AuctionCommand(String projectTitle){
        this.projectTitle = projectTitle;
    }

    public void execute(){
        JobInja.performAuction(this.projectTitle);
    }
}
