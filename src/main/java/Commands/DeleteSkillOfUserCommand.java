package Commands;
import Repositories.*;
import Entities.*;

public class DeleteSkillOfUserCommand implements Command{
    private String userID;
    private String skillName;

    public DeleteSkillOfUserCommand(String username, String name) {
        this.userID = username;
        this.skillName = name;
    }

    @Override
    public void execute() {
        UserRepo.endorse(userID, skillName);
    }

}

