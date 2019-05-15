package  Entities;

import java.util.*;

public class Project {
    private String id;
    private String title;
    private String description;
    private String imageURL;
    private HashMap<String, Skill> skills;
    private HashMap<String, Bid> bids;
    private int budget;
    private long deadline;
    private String winner;

    public Project(String id, String title, String description, String imageURL, int budget, long deadline, HashMap<String, Skill> skills, String winner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.budget = budget;
        this.deadline = deadline;
        this.skills = new HashMap<String, Skill> (skills);
        this.bids = new HashMap<>();
        this.winner = winner;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setSkills(HashMap<String, Skill> skills) {
        this.skills = skills;
    }

    public HashMap<String, Bid> getBids() {
        return bids;
    }

    public void setBids(HashMap<String, Bid> bids) {
        this.bids = bids;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setBudget(int budget){
        this.budget = budget;
    }

    public void addSkill(String name, Skill s){
        this.skills.put(name, s);
    }

    public HashMap<String, Skill> getSkills(){
        return this.skills;
    }

    public int getBudget(){
        return this.budget;
    }

    public String getTitle(){
        return this.title;
    }

    public void addBid(String username, String projectTitle, Integer biddingAmount){
        Bid b = new Bid(projectTitle, biddingAmount, username);
        this.bids.put(username, b);
    }

    public boolean hasBid(String username){
        return (this.bids.containsKey(username));
    }

}
