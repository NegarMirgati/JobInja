package Commands;

import Auctioneer.*;
import Exceptions.ProjectNotFoundException;
import Exceptions.UserNotFoundException;

public class AuctionCommand implements Command {
    private String projectTitle;

    public AuctionCommand(String projectTitle){
        this.projectTitle = projectTitle;
    }

    public void execute() throws UserNotFoundException {
        Auctioneer.performAuction(this.projectTitle);
    }
}
