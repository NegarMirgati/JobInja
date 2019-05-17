package Servlets;

import ContentProviders.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;

@WebServlet(name = "UsersServlet",  urlPatterns = { "/users" }, initParams = {
        @WebInitParam(name = "q" , value = "") })

public class UsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("q") == null || request.getParameter("q") == "") {
            response.setContentType("application/json;charset=UTF-8");
            JSONArray map = UserContentProvider.getHTMLContentsForAllUsers();
            response.setStatus(response.SC_OK);
            PrintWriter out = response.getWriter();
            out.println(map);
        }
        else{
            String q = request.getParameter("q");
            response.setContentType("application/json;charset=UTF-8");
            JSONArray map = UserContentProvider.getSearchedUsers(q);
            response.setStatus(response.SC_OK);
            PrintWriter out = response.getWriter();
            out.println(map);

        }

    }
}
