import java.util.HashMap;

public class AddProjectCommand implements Command{
    private String title;
    private HashMap<String, Integer> skills;
    private int budget;

    public AddProjectCommand(String title, HashMap<String, Integer> skills, int budget) {
        this.title = title;
        this.skills = new HashMap<String, Integer>(skills);
        this.budget = budget;
    }

    public void execute() {
        JobInja.addProject(this.title, this.skills, this.budget);
    }
}
