package Repositories;

import Entities.*;

import java.util.ArrayList;
import java.util.HashMap;

public class BidRepo {
    private static BidRepo ourInstance = new BidRepo();
    private static HashMap<String, ArrayList<Bid>> bidList = new HashMap<String, ArrayList<Bid>>();
    public static BidRepo getInstance() {
        return ourInstance;
    }

    private BidRepo() {
    }

    public static void addBid(String projectTitle, int biddingAmount, String biddingUser) {
        Bid newBid = new Bid( projectTitle, biddingAmount, biddingUser);
        if(!bidList.containsKey(projectTitle))
            bidList.put(projectTitle, new ArrayList<Bid>());
        bidList.get(projectTitle).add(newBid);
    }

    public static ArrayList<Bid> getBids(String projectTitle){ return bidList.get(projectTitle); }

    public static HashMap<String, ArrayList<Bid>> getBidList() {
        return bidList;
    }

}
