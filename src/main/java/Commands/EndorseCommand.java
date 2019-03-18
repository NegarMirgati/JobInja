package Commands;

import Exceptions.EndorseAlreadyDoneException;
import Exceptions.UserNotFoundException;
import Repositories.UserRepo;

public class EndorseCommand implements Command{
    private String userId;
    private String skillName;

    public EndorseCommand(String userId, String skillName) {
        this.userId = userId;
        this.skillName = skillName;
    }

    @Override
    public void execute() throws UserNotFoundException, EndorseAlreadyDoneException {
        UserRepo.endorse(this.userId, this.skillName);
    }

}
