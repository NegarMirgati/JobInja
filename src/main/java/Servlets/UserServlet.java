package Servlets;

import ContentProviders.userContentProvider;
import Exceptions.UserNotFoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import org.json.JSONObject;
import org.json.JSONArray;


@WebServlet(name = "UserServlet",  urlPatterns = { "/users/*" })
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean submitButtonPressed = request.getParameter("submit") != null;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String path = (request).getRequestURI();
        StringTokenizer tokenizer = new StringTokenizer(path, "/");
        String context = tokenizer.nextToken();
        String userId = tokenizer.nextToken();
        request.setAttribute("userID", userId);

        try {
            JSONObject map = userContentProvider.getHTMLContentsForUser(userId);
            JSONArray skills = userContentProvider.getUserSkills(userId);
            JSONArray extraSkills = userContentProvider.getExtraSkills(userId);
            PrintWriter out = response.getWriter();
            out.println(map);
            out.println(skills);
            out.println(extraSkills);
        }
        catch (UserNotFoundException e){
            request.setAttribute("exception", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error404.jsp");
            dispatcher.forward(request, response);
        }

    }
}