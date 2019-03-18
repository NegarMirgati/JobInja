package Servlets;

import ContentProviders.projectContentProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONArray;


@WebServlet(name = "ProjectsServlet",  urlPatterns = { "/projects" })
public class ProjectsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(response.SC_OK);
        JSONArray map = projectContentProvider.getHTMLContentsForAllProjects("1");
        PrintWriter out = response.getWriter();
        out.println(map);
    }
}
