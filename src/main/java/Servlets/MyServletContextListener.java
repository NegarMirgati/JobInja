package Servlets;
import DataLayer.DataMappers.Project.ProjectMapper;
import DataLayer.DataMappers.Skill.SkillMapper;
import DataLayer.DataMappers.user.UserMapper;
import DataLayer.DataMappers.user.UserSkillMapper;

import javax.servlet.*;
import java.io.IOException;
import java.sql.*;
import java.util.Enumeration;

public class MyServletContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("ServletContextListener started");
        try {
            SkillMapper sm = new SkillMapper();
            ProjectMapper pm = new ProjectMapper();
            UserSkillMapper usm = new UserSkillMapper();
            UserMapper um = new UserMapper();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

