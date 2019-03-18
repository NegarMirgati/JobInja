package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import Commands.EndorseCommand;
import ContentProviders.userContentProvider;
import Exceptions.EndorseAlreadyDoneException;
import Exceptions.UserNotFoundException;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.json.JSONArray;

@WebServlet(name = "endorse")
public class endorse extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String userID = request.getParameter("userID");
        String name = request.getParameter("name");
        System.out.println("userId: " +userID);
        System.out.println("name: " +name);

        EndorseCommand command = new EndorseCommand(userID, name);
        try {
            command.execute();
            JSONObject map = userContentProvider.getHTMLContentsForUser(userID);
            JSONArray skills = userContentProvider.getUserSkills(userID);

            request.setAttribute("content", map);
            request.setAttribute("skills", skills);
            request.setAttribute("userID", userID);

            PrintWriter out = response.getWriter();
            out.println(map);
            out.println(skills);

        }
        catch (EndorseAlreadyDoneException e){
            request.setAttribute("exception", e);
            JSONObject instance = new JSONObject();
            instance.put("status", 409);
            instance.put("message", e.getMessage());
            instance.put("developerMessage", "User has already endorsed this skill");
            PrintWriter out = response.getWriter();
            out.println(instance);
            response.setStatus(response.SC_CONFLICT);
        }
        catch(UserNotFoundException e){
            request.setAttribute("exception", e);
            response.setStatus(response.SC_NOT_FOUND);
            JSONObject instance = new JSONObject();
            instance.put("status", 404);
            instance.put("message", e.getMessage());
            PrintWriter out = response.getWriter();
            out.println(instance);
            //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error404.jsp");
            //dispatcher.forward(request, response);
        }

    }
}
