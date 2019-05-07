package Servlets;

import ContentProviders.ProjectContentProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import Exceptions.ProjectNotFoundException;
import org.json.JSONArray;


@WebServlet(name = "ProjectsServlet",  urlPatterns = { "/projects" } )

public class ProjectsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(response.SC_NOT_IMPLEMENTED);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("application/json;charset=UTF-8");
            System.out.println("in ProjectsServlet");
            response.setStatus(response.SC_OK);
            JSONArray map = null;
            try {
                map = ProjectContentProvider.getContentsForAllProjects();
            } catch (ProjectNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PrintWriter out = response.getWriter();
            out.println(map);

    }
}
