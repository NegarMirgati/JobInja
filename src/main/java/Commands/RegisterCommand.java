package Commands;
import Repositories.*;
import Entities.*;

import java.util.HashMap;

public class RegisterCommand implements Command{
    private String username;
    private HashMap<String, Skill> skills;

    public RegisterCommand(String username, HashMap<String, Skill> skills) {
        this.username = username;
        this.skills = new HashMap<String, Skill>(skills);
    }

    public void execute() { UserRepo.addUser(this.username, this.skills);
    }

}
