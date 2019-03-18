package Commands;

import Exceptions.SkillNotFoundException;
import Exceptions.UserNotFoundException;

public interface Command {
    public void execute() throws UserNotFoundException, SkillNotFoundException;
}
