import java.util.HashMap;

public class Bid {
    private String projectTitle;
    private int biddingAmount;
    private String biddingUser;

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
