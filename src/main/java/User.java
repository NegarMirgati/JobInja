import java.util.HashMap;

public class User {
    private String username;
    private HashMap<String, Integer> skills;

    public void setUsername(String username){
        this.username = username;
    }

    public void addSkill(String name, int score){
        this.skills.put(name, score);
    }

    public HashMap<String, Integer> getSkills(){
        return this.skills;
    }

    public String getUsername(){
        return this.username;
    }

}
