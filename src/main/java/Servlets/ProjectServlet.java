package Servlets;
import Exceptions.*;
import ContentProviders.*;

import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import org.json.JSONObject;




@WebServlet(  name = "ProjectServlet",  urlPatterns = { "/project"} , initParams = {
        @WebInitParam(name = "id" , value = "Not provided") } )


public class ProjectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("application/json;charset=UTF-8");
        String pathInfo = request.getPathInfo();
        String path = (request).getRequestURI();
        //StringTokenizer tokenizer = new StringTokenizer(path, "/");
        //String context = tokenizer.nextToken();
       // String projectID = tokenizer.nextToken();
        String projectID = request.getParameter("id");
        request.setAttribute("projectID", projectID);
        boolean hasBade = false;

        try {
            JSONObject map = projectContentProvider.getHTMLContentsForProject("1", projectID);
            projectContentProvider.checkAccess("1",projectID);
            response.setStatus(response.SC_OK);
            PrintWriter out = response.getWriter();
            out.println(map);

        } catch (ProjectNotFoundException e) {
            request.setAttribute("exception", e);
            JSONObject instance = new JSONObject();
            instance.put("status", 404);
            instance.put("message", e.getMessage());
            PrintWriter out = response.getWriter();
            out.println(instance);
            response.setStatus(response.SC_NOT_FOUND);
        }

        catch (ProjectAccessForbiddenException e){
            request.setAttribute("exception", e);
            JSONObject instance = new JSONObject();
            instance.put("status", 403);
            instance.put("message", e.getMessage());
            instance.put("developerMessage", "User does not have minimum skills to view this project");
            PrintWriter out = response.getWriter();
            out.println(instance);
            response.setStatus(response.SC_FORBIDDEN);
        }
    }
}
