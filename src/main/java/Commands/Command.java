package Commands;

import Exceptions.EndorseAlreadyDoneException;
import Exceptions.SkillNotFoundException;
import Exceptions.UserNotFoundException;

public interface Command {
    public void execute() throws UserNotFoundException, SkillNotFoundException, EndorseAlreadyDoneException;
}
