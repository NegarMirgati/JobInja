package Auctioneer;

import ContentProviders.ProjectContentProvider;
import DataLayer.DataMappers.Project.ProjectMapper;
import Entities.Project;
import Entities.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuctionScheduler implements Runnable {
    @Override
    public void run() {
        System.out.println("Auction begins");
        ArrayList<Project> found = ProjectContentProvider.getFinishedProjects();
        System.out.println("founbddddddd");
        System.out.println(found.size());
       // System.out.println(found.get(1).getDeadline());
        System.out.println(System.currentTimeMillis());
        try {
            ProjectMapper pm = new ProjectMapper(false);
            for (Project p: found){
                User u = Auctioneer.performAuction(p);
                if (u != null){
                    System.out.println("u.getUsername() of winner ");
                    System.out.println(u.getUsername());
                    pm.updateWinner(u.getUsername(),p.getId());
                }
                else {
                    pm.updateWinner("NULL", p.getId());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

