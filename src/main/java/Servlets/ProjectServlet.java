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

        try {
            map = projectContentProvider.getHTMLContentsForProject("1", "20955d46-0aac-4a6a-b546-d1581026663f");
        } catch (ProjectNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("content", map);
        System.out.println(request.getAttribute("content"));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/project.jsp");
        dispatcher.forward(request, response);
    }
}
