package Servlets;

import ContentProviders.userContentProvider;
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
import java.util.StringTokenizer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.json.JSONArray;


@WebServlet(  name = "UserServlet",  urlPatterns = { "/user"} , initParams = {
        @WebInitParam(name = "id" , value = "Not provided") } )
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setStatus(response.SC_NOT_IMPLEMENTED);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String path = (request).getRequestURI();
        String userId = request.getParameter("id");
        request.setAttribute("userID", userId);

        try {
            JSONObject map = userContentProvider.getHTMLContentsForUser(userId);
            //JSONArray skills = userContentProvider.getUserSkills(userId);
            response.setStatus(response.SC_OK);
            PrintWriter out = response.getWriter();
            out.println(map);
            //out.println(skills);
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