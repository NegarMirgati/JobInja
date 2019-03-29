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

@WebServlet(name = "addSkill", urlPatterns = { "/user/addSkill"} , initParams = {
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
        System.out.println("id:" + userID);
        System.out.println("name:" + selectedSkill);

        try {
            userContentProvider.validateSkill(selectedSkill);
            userContentProvider.checkCurrentUser(userID);
            AddSkillToUserCommand command = new AddSkillToUserCommand(userID, selectedSkill);
            command.execute();
            JSONObject status = new JSONObject();
            status.put("status", "200");
            status.put("message", "skill successfully added");
            System.out.println("skill successfully added " + selectedSkill);
            PrintWriter out = response.getWriter();
            out.println(status);
        }
        catch( UserNotFoundException e){
            printApiOutputError(e, 404,response);

        } catch (UserAccessForbidden e) {
            printApiOutputError(e, 403,response);

        } catch (AddSkillAlreadyDoneException e) {
            printApiOutputError(e, 409,response);
        }
        catch(InvalidSkillException e){
            printApiOutputError(e, 422,response);

        }
    }

    private void printApiOutputError(Throwable e, int statusCode, HttpServletResponse response) throws IOException{
        JSONObject instance = new JSONObject();
        instance.put("status", statusCode);
        instance.put("message", e.getMessage());
        response.setStatus(statusCode);
        PrintWriter out = response.getWriter();
        out.println(instance);
    }
}
