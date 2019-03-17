package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

import Commands.EndorseCommand;
import ContentProviders.userContentProvider;
import Exceptions.UserNotFoundException;

@WebServlet(name = "endorse")
public class endorse extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String name = request.getParameter("name");
        System.out.println("userId: " +userID);
        System.out.println("name: " +name);

        EndorseCommand command = new EndorseCommand(userID, name);
        try {
            command.execute();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        HashMap<String, String> map = new HashMap<String, String>();
        HashMap<String, String> skills = new HashMap<String, String>();

        try {
            map = userContentProvider.getHTMLContentsForUser(userID);
            skills = userContentProvider.getUserSkills(userID);

            request.setAttribute("content", map);
            request.setAttribute("skills", skills);
            request.setAttribute("userID", userID);

            System.out.println(request.getAttribute("content"));
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user.jsp");
            dispatcher.forward(request, response);

        }catch(
                UserNotFoundException e){
            request.setAttribute("exception", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error404.jsp");
            dispatcher.forward(request, response);
        }

    }
}
