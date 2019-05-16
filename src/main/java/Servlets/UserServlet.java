package Servlets;

import Commands.RegisterCommand;
import ContentProviders.UserContentProvider;
import Exceptions.UserNotFoundException;

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
import java.util.StringTokenizer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.json.JSONArray;


@WebServlet(  name = "UserServlet",  urlPatterns = { "/user"} , initParams = {
        @WebInitParam(name = "id" , value = "Not provided") } )
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String jobTitle = request.getParameter("jobTitle");
        String bio = request.getParameter("bio");
        String proLink= request.getParameter("proLink");
        String password = request.getParameter("password");
        RegisterCommand regCmd = new RegisterCommand(userId, password, firstName, lastName, jobTitle,  bio, proLink);
        try {
            regCmd.execute();
            response.setStatus(response.SC_OK);
            JSONObject instance = new JSONObject();
            instance.put("status", 200);
            instance.put("message", "success");
            PrintWriter out = response.getWriter();
            out.println(instance);

        }catch(SQLException e){
            response.setStatus(response.SC_CONFLICT);
            JSONObject instance = new JSONObject();
            instance.put("status", "200");
            instance.put("message", e.getMessage());
            PrintWriter out = response.getWriter();
            out.println(instance);
            request.setAttribute("exception", e);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String path = (request).getRequestURI();
        String userId = request.getParameter("id");
        String myId = (String) request.getAttribute("username");
        request.setAttribute("userID", userId);
        System.out.println("myId : " + myId + " userId : " + userId);

        try {
            JSONObject map = UserContentProvider.getHTMLContentsForUser(myId, userId);
            response.setStatus(response.SC_OK);
            PrintWriter out = response.getWriter();
            out.println(map);
        }
        catch (UserNotFoundException e){
            response.setStatus(response.SC_NOT_FOUND);
            JSONObject instance = new JSONObject();
            instance.put("status", 404);
            instance.put("message", e.getMessage());
            PrintWriter out = response.getWriter();
            out.println(instance);
            request.setAttribute("exception", e);
        }
    }
}