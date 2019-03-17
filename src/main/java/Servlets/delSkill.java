package Servlets;

import ContentProviders.userContentProvider;
import Commands.DeleteSkillOfUserCommand;
import Exceptions.UserNotFoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "delSkill")
public class delSkill extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userID = request.getParameter("userID");
        String name = request.getParameter("name");
        System.out.println("userId: " +userID);
        System.out.println("name: " +name);

        DeleteSkillOfUserCommand command = new DeleteSkillOfUserCommand(userID, name);
        try {
            command.execute();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        try {
            HashMap<String, String> map = new HashMap<>(userContentProvider.getHTMLContentsForUser(userID));
            HashMap<String, String> skills = new HashMap<>(userContentProvider.getUserSkills(userID));
            HashMap<String, String> extraSkills = new HashMap<>(userContentProvider.getExtraSkills(userID));

            request.setAttribute("content", map);
            request.setAttribute("skills", skills);
            request.setAttribute("extraSkills", extraSkills);
            request.setAttribute("userID", userID);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user.jsp");
            dispatcher.forward(request, response);
        }
    catch(
    UserNotFoundException e){
        request.setAttribute("exception", e);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error404.jsp");
        dispatcher.forward(request, response);
    }

    }

}
