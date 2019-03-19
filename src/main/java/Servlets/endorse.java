package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import Commands.EndorseCommand;
import ContentProviders.userContentProvider;
import Exceptions.*;
import org.json.JSONObject;
import org.json.JSONArray;

@WebServlet(name = "endorse", urlPatterns = { "/user/endorse"} , initParams = {
    @WebInitParam(name = "id" , value = "Not provided"),
        @WebInitParam(name = "name" , value = "Not provided")} )

public class endorse extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(response.SC_NOT_IMPLEMENTED);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String userID = request.getParameter("id");
        String name = request.getParameter("name");
        System.out.println("id: " +userID);
        System.out.println("name: " +name);

        EndorseCommand command = new EndorseCommand(userID, name);
        try {
            if(userID != null && userID.equals("1"))
                throw new UserAccessForbidden("Forbidden endorse");

            userContentProvider.validateSkill(name);
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
