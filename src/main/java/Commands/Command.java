package Commands;

import Exceptions.*;

import java.sql.SQLException;

public interface Command {
    public void execute() throws UserNotFoundException, SkillNotFoundException, EndorseAlreadyDoneException, AddSkillAlreadyDoneException, SQLException;
}
