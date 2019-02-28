package Servlets;

import ContentProviders.userContentProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String pathInfo = request.getPathInfo();
        String userId = request.getAttribute("userID").toString();
        System.out.println("userID" + userId);
        System.out.println("paaaattttghhhh" + pathInfo);
        HashMap<String, String> map = new HashMap<>();


        map = userContentProvider.getHTMLContentsForUser(userId);

        request.setAttribute("content", map);
        System.out.println(request.getAttribute("content"));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user.jsp");
        dispatcher.forward(request, response);

    }
}