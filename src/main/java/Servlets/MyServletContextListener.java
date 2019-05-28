package Servlets;
import DataLayer.DataMappers.Project.ProjectMapper;
import DataLayer.DataMappers.Skill.SkillMapper;
import DataLayer.DataMappers.user.EndorsementMapper;
import DataLayer.DataMappers.user.UserMapper;
import DataLayer.DataMappers.user.UserSkillMapper;

import javax.servlet.*;
import java.io.IOException;
import java.sql.*;
import Entities.User;

public class MyServletContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("ServletContextListener started");
        try {
            SkillMapper sm = new SkillMapper();
            sm.initialize();
            UserMapper um = new UserMapper();
            um.initialize();
            UserSkillMapper usm = new UserSkillMapper();
            usm.initialize();
            EndorsementMapper em = new EndorsementMapper();
            em.initialize();
            ProjectMapper pm = new ProjectMapper(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

