package Commands;

import Exceptions.*;

public interface Command {
    public void execute() throws UserNotFoundException, SkillNotFoundException, EndorseAlreadyDoneException, AddSkillAlreadyDoneException;
}
