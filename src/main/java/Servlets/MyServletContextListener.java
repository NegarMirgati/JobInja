package Servlets;
import DataLayer.DataMappers.Project.ProjectMapper;
import DataLayer.DataMappers.Skill.SkillMapper;

import javax.servlet.*;
import java.io.IOException;
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
            SkillMapper sm = new SkillMapper();
            ProjectMapper pm = new ProjectMapper();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ProjectRepo.addProjects();
//        SkillRepo.addSkills();
//        UserRepo.addUser();
//        UserRepo.addUser2();
//        UserRepo.addUser3();
    }
}

