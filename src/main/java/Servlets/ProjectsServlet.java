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


@WebServlet(name = "ProjectsServlet",  urlPatterns = { "/projects" }, initParams = {
        @WebInitParam(name = "q" , value = "") })
public class ProjectsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(response.SC_NOT_IMPLEMENTED);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("q") == "" || request.getParameter("q").equals("")) {
            response.setContentType("application/json;charset=UTF-8");
            System.out.println("in ProjectsServlet");
            response.setStatus(response.SC_OK);
            JSONArray map = null;
            try {
                System.out.println("here in projectsservlet for q null");
                map = ProjectContentProvider.getContentsForAllProjects();
            } catch (ProjectNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PrintWriter out = response.getWriter();
            out.println(map);
        }
        else {
            response.setContentType("application/json;charset=UTF-8");
            String q = request.getParameter("q");
            System.out.println("qqq");
            System.out.println(q);
            JSONArray map = ProjectContentProvider.getSearchedProjects(q);
            response.setStatus(response.SC_OK);
            PrintWriter out = response.getWriter();
            out.println(map);
        }

    }
}
