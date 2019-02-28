package Servlets;
import Repositories.*;
import javax.servlet.*;


public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        //Notification that the servlet context is about to be shut down.
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("ServletContextListener started");
        ProjectRepo.addProjects();
        SkillRepo.addSkills();
        UserRepo.addUser();
        UserRepo.addUser2();
        UserRepo.addUser3();
    }
}

