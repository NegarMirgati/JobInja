package Entities;

public class Bid{
    private String projectTitle;
    private int biddingAmount;
    private String biddingUser;

    public Bid(String projectTitle, int biddingAmount, String biddingUser) {
        this.projectTitle = projectTitle;
        this.biddingAmount = biddingAmount;
        this.biddingUser = biddingUser;
    }

    public void setProjectTitle(String title){
        this.projectTitle = title;
    }

    public void setBiddingAmount(int budget){
        this.biddingAmount = budget;
    }

    public void setBiddingUser(String usernme){
        this.biddingUser = usernme;
    }

    public int getBiddingAmount(){
        return this.biddingAmount;
    }

    public String getProjectTitle(){
        return this.projectTitle;
    }

    public String getBiddingUser(){
        return this.biddingUser;
    }
}
