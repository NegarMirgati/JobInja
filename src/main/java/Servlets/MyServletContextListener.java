package Servlets;
import DataBaseHandler.DataBaseConnection;
import DataBaseHandler.SkillsHandler;
import Repositories.*;
import javax.servlet.*;
import java.sql.SQLException;


public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        //Notification that the servlet context is about to be shut down.
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("ServletContextListener started");
        try {
            DataBaseConnection.connectToDB();
            SkillsHandler.createSkillsTable();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        ProjectRepo.addProjects();
//        SkillRepo.addSkills();
//        UserRepo.addUser();
//        UserRepo.addUser2();
//        UserRepo.addUser3();
    }
}

