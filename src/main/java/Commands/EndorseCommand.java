package Commands;

import Repositories.UserRepo;

public class EndorseCommand implements Command{
    private String userId;
    private String skillName;

    public EndorseCommand(String userId, String skillName) {
        this.userId = userId;
        this.skillName = skillName;
    }

    @Override
    public void execute() {
        UserRepo.endorse(this.userId, this.skillName);
    }

}
