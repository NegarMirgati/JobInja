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



@WebServlet(name = "ProjectServlet")
public class ProjectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        String pathInfo = request.getPathInfo();
        System.out.println("paaaattttghhhh" + pathInfo);
        HashMap<String, String> map = new HashMap<>();
        map.put("salam", "khodafeeeez");
        map.put("salam1", "khodafeeeez1");
        map.put("salam2", "khodafeeeez2");
        request.setAttribute("content", map);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/project.jsp");
        dispatcher.forward(request, response);
    }
}
