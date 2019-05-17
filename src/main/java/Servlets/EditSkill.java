package Servlets;

import Commands.AddSkillToUserCommand;
import Commands.EndorseCommand;
import ContentProviders.UserContentProvider;
import Commands.DeleteSkillOfUserCommand;
import Exceptions.*;

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

@WebServlet(name = "EditSkill" ,   urlPatterns = { "/user/skill"} , initParams = {
        @WebInitParam(name = "name" , value = "Not provided"),
        @WebInitParam(name = "id" , value = "Not provided")} )

public class EditSkill extends HttpServlet {

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String name = request.getParameter("name");
        String userID = (String) request.getAttribute("username");
        System.out.println("userID: " +userID);
        System.out.println("name: " +name);
        String urlId = request.getParameter("id");

        try {
            if(userID == null)
                throw new UserNotFoundException("user not found");
            else if(name == null)
                throw new SkillNotFoundException("skill not found");

            UserContentProvider.checkCurrentUser(userID, urlId);
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

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String userID = (String) request.getAttribute("username");
        String selectedSkill = request.getParameter("name");
        System.out.println("id:" + userID);
        System.out.println("name:" + selectedSkill);
        String urlId = request.getParameter("id");

        try {
            UserContentProvider.validateSkill(selectedSkill);
            UserContentProvider.checkCurrentUser(userID, urlId);
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String userID = request.getParameter("id");
        String name = request.getParameter("name");
        String endrorserID = (String) request.getAttribute("username");
        System.out.println("endorsedId: " +userID);
        System.out.println("endorserId: " +endrorserID);
        System.out.println("name: " +name);

        EndorseCommand command = new EndorseCommand(userID, endrorserID, name);
        try {
            if(userID != null && userID.equals(endrorserID))
                throw new UserAccessForbidden("Forbidden endorse");

            UserContentProvider.validateSkill(name);
            command.execute();
            response.setStatus(response.SC_OK);
            JSONObject instance = new JSONObject();
            instance.put("status", 200);
            instance.put("message", "Endorse successfully done");
            PrintWriter out = response.getWriter();
            out.println(instance);
        }
        catch (EndorseAlreadyDoneException e){
            printApiOutputError(e, 409,response);
        }
        catch(UserNotFoundException e){
            printApiOutputError(e, 404,response);
        }
        catch (InvalidSkillException e){

           printApiOutputError(e, 422,response);
       }
        catch (UserAccessForbidden e){

            printApiOutputError(e, 422,response);
        }
        catch (SkillNotFoundException e){
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
