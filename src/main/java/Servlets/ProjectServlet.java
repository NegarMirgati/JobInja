package Servlets;
import DataLayer.DataMappers.Project.ProjectMapper;
import Entities.Project;
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
import java.sql.SQLException;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import org.json.JSONObject;




@WebServlet(  name = "ProjectServlet",  urlPatterns = { "/project"} , initParams = {
        @WebInitParam(name = "id" , value = "Not provided") } )


public class ProjectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(response.SC_NOT_IMPLEMENTED);

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
            //JSONObject map = projectContentProvider.getHTMLContentsForProject("1", projectID);
            ProjectMapper pm = new ProjectMapper(false);
            Project p = pm.find(projectID);
            JSONObject map = ProjectContentProvider.getProjectContent(p);
            //ProjectContentProvider.checkAccess("1",projectID);
            response.setStatus(response.SC_OK);
            PrintWriter out = response.getWriter();
            out.println(map);

        } catch (ProjectNotFoundException e) {
            printApiOutputError(e, 404,response);
        }

//        catch (ProjectAccessForbiddenException e){
//            printApiOutputError(e, 403,response);
//        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printApiOutputError(Throwable e, int statusCode, HttpServletResponse response) throws IOException{
        JSONObject instance = new JSONObject();
        instance.put("status", statusCode);
        instance.put("message", e.getMessage());
        response.setStatus(statusCode);
        PrintWriter out = response.getWriter();
        out.println(instance);
    }
}
