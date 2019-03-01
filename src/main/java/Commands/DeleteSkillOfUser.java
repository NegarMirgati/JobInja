package Commands;
import Repositories.*;
import Entities.*;

public class DeleteSkillOfUser implements Command{
    private String userID;
    private String skillName;

    public DeleteSkillOfUser(String username, String name) {
        this.userID = username;
        this.skillName = name;
    }

    public void execute() {
        User u = UserRepo.getUserById(userID);
        u.delSkill(skillName);
    }

}

