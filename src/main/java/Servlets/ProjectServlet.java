package Servlets;
import Exceptions.*;
import ContentProviders.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import org.json.JSONObject;




@WebServlet(name = "ProjectServlet",  urlPatterns = { "/projects/*" })
public class ProjectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("application/json;charset=UTF-8");
        String pathInfo = request.getPathInfo();
        String path = (request).getRequestURI();
        StringTokenizer tokenizer = new StringTokenizer(path, "/");
        String context = tokenizer.nextToken();
        String projectID = tokenizer.nextToken();
        request.setAttribute("projectID", projectID);
        boolean hasBade = false;

        try {
            hasBade = projectContentProvider.hasBadeForProject("1", projectID);
            JSONObject map = projectContentProvider.getHTMLContentsForProject("1", projectID);
            projectContentProvider.checkAccess("1",projectID);
            response.setStatus(response.SC_OK);
            PrintWriter out = response.getWriter();
            out.println(map);

        } catch (ProjectNotFoundException e) {
            request.setAttribute("exception", e);
            response.setStatus(response.SC_NOT_FOUND);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error404.jsp");
            dispatcher.forward(request, response);
        }

        catch (ProjectAccessForbiddenException e){
            request.setAttribute("exception", e);
            response.setStatus(response.SC_FORBIDDEN);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error403.jsp");
            dispatcher.forward(request, response);

        }
    }
}
