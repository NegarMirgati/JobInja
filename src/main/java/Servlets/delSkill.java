package Servlets;

import ContentProviders.userContentProvider;
import Commands.DeleteSkillOfUserCommand;
import Exceptions.SkillNotFoundException;
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
import org.json.JSONObject;
import org.json.JSONArray;

@WebServlet(name = "delSkill" ,   urlPatterns = { "/user/delSkill"} , initParams = {
        @WebInitParam(name = "id" , value = "Not provided"),
        @WebInitParam(name = "name" , value = "Not provided")} )

public class delSkill extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(response.SC_NOT_IMPLEMENTED);

    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String userID = request.getParameter("id");
        String name = request.getParameter("name");
        System.out.println("userId: " +userID);
        System.out.println("name: " +name);

        try {
            if(userID == null)
                throw new UserNotFoundException("user not found");
            else if(name == null)
                throw new SkillNotFoundException("skill not found");

            userContentProvider.checkCurrentUser(userID);
            DeleteSkillOfUserCommand command = new DeleteSkillOfUserCommand(userID, name);
            command.execute();
            JSONObject status = new JSONObject();
            status.put("status", "delete was done successfully");
            response.setStatus(response.SC_OK);
            PrintWriter out = response.getWriter();
            out.println(status);

        } catch (UserAccessForbidden e) {
            printApiOutputError(e, 403,response);

        } catch (UserNotFoundException | SkillNotFoundException e){
            printApiOutputError(e, 404,response);
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
