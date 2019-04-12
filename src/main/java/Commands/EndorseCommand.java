package Commands;

import Exceptions.EndorseAlreadyDoneException;
import Exceptions.SkillNotFoundException;
import Exceptions.UserNotFoundException;
import Repositories.UserRepo;

public class EndorseCommand implements Command{
    private String userId;
    private String skillName;
    private String endorserId;

    public EndorseCommand(String userId, String endorserId, String skillName) {
        this.userId = userId;
        this.skillName = skillName;
        this.endorserId = endorserId;
    }

    @Override
    public void execute() throws UserNotFoundException, EndorseAlreadyDoneException, SkillNotFoundException {
        UserRepo.endorse(this.userId, this.endorserId, this.skillName);
    }

}
