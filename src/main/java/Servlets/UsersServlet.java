package Servlets;

import ContentProviders.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "UsersServlet")
public class UsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, HashMap<String, String>> map = userContentProvider.getHTMLContentsForAllUsers("1");
        request.setAttribute("content", map);
        System.out.println(request.getAttribute("content"));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/users.jsp");
        dispatcher.forward(request, response);


    }
}
