package Commands;

import Repositories.*;

public class AddSkillToUserCommand implements Command {
    private String uId;
    private String skillName;

    public AddSkillToUserCommand(String uId, String skillName) {
        this.uId = uId;
        this.skillName = skillName;
    }

    public void execute(){
      UserRepo.addSkillToUser(uId, skillName);
    }
}
