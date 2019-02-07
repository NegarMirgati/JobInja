import java.util.*;
import java.io.*;

public class Project {
    private String title;
    private HashMap<String, Integer> skills;
    private int budget;

    public Project(String title, HashMap<String, Integer> skills, int budget) {
        this.title = title;
        this.skills = skills;
        this.budget = budget;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setBudget(int budget){
        this.budget = budget;

    }

    public void addSkill(String name, int score){
        this.skills.put(name, score);
    }

    public HashMap<String, Integer> getSkills(){
        return this.skills;
    }

    public int getBudget(){
        return this.budget;
    }

    public String getTitle(){
        return this.title;
    }

}
