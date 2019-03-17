package Servlets;

import Commands.AddSkillToUserCommand;
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
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "addSkill")
public class addSkill extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String userID = request.getParameter("userID");
        String selectedSkill = request.getParameter("selectedSkill");
        System.out.println("userId: " +userID);
        System.out.println("selectedSkill: " +selectedSkill);

        AddSkillToUserCommand command = new AddSkillToUserCommand(userID, selectedSkill);
        try {
            command.execute();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        try {
            JSONObject map = userContentProvider.getHTMLContentsForUser(userID);
            JSONArray skills = userContentProvider.getUserSkills(userID);
            JSONArray extraSkills = userContentProvider.getExtraSkills(userID);

            request.setAttribute("content", map);
            request.setAttribute("skills",skills);
            request.setAttribute("extraSkills", extraSkills);
            request.setAttribute("userID", userID);

            PrintWriter out = response.getWriter();
            out.println(map);
            out.println(extraSkills);
            out.println(skills);
            out.println(selectedSkill);

        }
        catch(
                UserNotFoundException e){
            request.setAttribute("exception", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error404.jsp");
            dispatcher.forward(request, response);
        }
    }
}
