package Commands;

import Exceptions.AddSkillAlreadyDoneException;
import Exceptions.UserNotFoundException;
import Repositories.*;

public class AddSkillToUserCommand implements Command {
    private String uId;
    private String skillName;

    public AddSkillToUserCommand(String uId, String skillName) {
        this.uId = uId;
        this.skillName = skillName;
    }

    public void execute() throws UserNotFoundException, AddSkillAlreadyDoneException {
      UserRepo.addSkillToUser(uId, skillName);
    }
}
