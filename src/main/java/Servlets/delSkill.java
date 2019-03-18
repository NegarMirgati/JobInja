package Servlets;

import ContentProviders.userContentProvider;
import Commands.DeleteSkillOfUserCommand;
import Exceptions.SkillNotFoundException;
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
import org.json.JSONObject;
import org.json.JSONArray;

@WebServlet(name = "delSkill")
public class delSkill extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String userID = request.getParameter("userID");
        String name = request.getParameter("name");
        System.out.println("userId: " +userID);
        System.out.println("name: " +name);

        DeleteSkillOfUserCommand command = new DeleteSkillOfUserCommand(userID, name);
        try {
            command.execute();
        } catch (SkillNotFoundException |
                 UserNotFoundException e){
            request.setAttribute("exception", e);
            JSONObject instance = new JSONObject();
            instance.put("status", 404);
            instance.put("message", e.getMessage());
            PrintWriter out = response.getWriter();
            out.println(instance);
            response.setStatus(response.SC_NOT_FOUND);
        }

        try {
            JSONObject map = userContentProvider.getHTMLContentsForUser(userID);
            JSONArray skills = userContentProvider.getUserSkills(userID);
            JSONArray extraSkills = userContentProvider.getExtraSkills(userID);

            request.setAttribute("content", map);
            request.setAttribute("skills", skills);
            request.setAttribute("extraSkills", extraSkills);
            request.setAttribute("userID", userID);

            PrintWriter out = response.getWriter();
            out.println(map);

        }
        catch(UserNotFoundException e){
            request.setAttribute("exception", e);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error404.jsp");
            dispatcher.forward(request, response);
        }

    }

}
