package Commands;

import Auctioneer.*;

public class AuctionCommand implements Command {
    private String projectTitle;

    public AuctionCommand(String projectTitle){
        this.projectTitle = projectTitle;
    }

    public void execute(){
        Auctioneer.performAuction(this.projectTitle);
    }
}
