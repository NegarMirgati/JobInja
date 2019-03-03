package Commands;

import Exceptions.UserNotFoundException;

public interface Command {
    public void execute() throws UserNotFoundException;
}
