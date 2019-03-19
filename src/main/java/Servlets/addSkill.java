package Servlets;

import Commands.AddSkillToUserCommand;
import ContentProviders.userContentProvider;
import Exceptions.AddSkillAlreadyDoneException;
import Exceptions.InvalidSkillException;
import Exceptions.UserAccessForbidden;
import Exceptions.UserNotFoundException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "addSkill", urlPatterns = { "/users/addSkill"} , initParams = {
        @WebInitParam(name = "id" , value = "Not provided"),
        @WebInitParam(name = "name" , value = "Not provided")} )

public class addSkill extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(response.SC_NOT_IMPLEMENTED);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String userID = request.getParameter("id");
        String selectedSkill = request.getParameter("name");



        try {
            userContentProvider.validateSkill(selectedSkill);
            userContentProvider.checkCurrentUser(userID);
            AddSkillToUserCommand command = new AddSkillToUserCommand(userID, selectedSkill);
            command.execute();
            JSONObject status = new JSONObject();
            status.put("status", "skill was added successfully");
            PrintWriter out = response.getWriter();
            out.println(status);
        }
        catch( UserNotFoundException e){
            request.setAttribute("exception", e);
            JSONObject instance = new JSONObject();
            instance.put("status", 404);
            instance.put("message", e.getMessage());
            PrintWriter out = response.getWriter();
            out.println(instance);
            response.setStatus(response.SC_NOT_FOUND);

        } catch (UserAccessForbidden userAccessForbidden) {
            JSONObject instance = new JSONObject();
            response.setStatus(403);
            instance.put("status", 403);
            instance.put("message", userAccessForbidden.getMessage());
            PrintWriter out = response.getWriter();
            out.println(instance);

        } catch (AddSkillAlreadyDoneException e) {
            request.setAttribute("exception", e);
            JSONObject instance = new JSONObject();
            response.setStatus(409);
            instance.put("status", 409);
            instance.put("message", e.getMessage());
            PrintWriter out = response.getWriter();
            out.println(instance);
        }
        catch(InvalidSkillException e){
            request.setAttribute("exception", e);
            JSONObject instance = new JSONObject();
            instance.put("status", 422);
            instance.put("message", e.getMessage());
            instance.put("developerMessage", "This skill is not in the list of valid skills for users.");
            response.setStatus(422);
            PrintWriter out = response.getWriter();
            out.println(instance);

        }
    }
}
