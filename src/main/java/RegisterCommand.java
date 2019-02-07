import java.util.HashMap;

public class RegisterCommand implements Command{
    private String username;
    private HashMap<String, Integer> skills;

    public RegisterCommand(String username, HashMap<String, Integer> skills) {
        this.username = username;
        this.skills = new HashMap<String, Integer>(skills);
    }

    public void execute() {
       JobInja.addUser(this.username, this.skills);
    }

}
