package Servlets;
import Exceptions.*;
import ContentProviders.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import java.util.StringTokenizer;



@WebServlet(name = "ProjectServlet",  urlPatterns = { "/projects/*" })
public class ProjectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        String pathInfo = request.getPathInfo();
        String path = (request).getRequestURI();
        HashMap<String, String> map = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(path, "/");
        String context = tokenizer.nextToken();
        String projectID = tokenizer.nextToken();
        request.setAttribute("projectID", projectID);
        boolean hasBade = false;

        try {
            hasBade = projectContentProvider.hasBadeForProject("1", projectID);
            map = projectContentProvider.getHTMLContentsForProject("1", projectID);
            projectContentProvider.checkAccess("1",projectID);
            request.setAttribute("content", map);
            request.setAttribute("hasBadeForThisProject", hasBade);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/project.jsp");
            dispatcher.forward(request, response);

        } catch (ProjectNotFoundException e) {
            request.setAttribute("exception", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error404.jsp");
            dispatcher.forward(request, response);
        }

        catch (ProjectAccessForbiddenException e){
            request.setAttribute("exception", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error403.jsp");
            dispatcher.forward(request, response);

        }
    }
}
