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

import Commands.*;
import ContentProviders.projectContentProvider;
import Exceptions.BidAlreadyDoneException;
import Exceptions.ProjectAccessForbiddenException;
import Exceptions.ProjectNotFoundException;
import Exceptions.UserNotFoundException;
import org.json.JSONObject;

@WebServlet(  name = "Bid",  urlPatterns = { "/project/bid"} , initParams = {
        @WebInitParam(name = "id" , value = "Not provided"),
        @WebInitParam(name = "amount" , value = "Not provided")} )

public class Bid extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bidAmount = request.getParameter("amount");
        String projectID = request.getParameter("id");
        BidCommand bidCommand = new BidCommand(projectID, Integer.valueOf(bidAmount), "1");
        response.setContentType("application/json;charset=UTF-8");
        try {
            projectContentProvider.hasBadeForProject("1", projectID);
            projectContentProvider.checkAccess("1", projectID);
            if(!bidCommand.bidIsPossible()){
                response.setStatus(response.SC_BAD_REQUEST);
                JSONObject status = new JSONObject();
                status.put("status", "Invalid bid amount");
                PrintWriter out = response.getWriter();
                out.println(status);
            }
            else{
                bidCommand.execute();
                response.setStatus(response.SC_OK);
                JSONObject status = new JSONObject();
                status.put("status", "bid successfully done.");
                PrintWriter out = response.getWriter();
                out.println(status);

            }
        } catch (UserNotFoundException |
                ProjectNotFoundException e) {
            JSONObject instance = new JSONObject();
            instance.put("status", 404);
            instance.put("message", e.getMessage());
            PrintWriter out = response.getWriter();
            out.println(instance);
            response.setStatus(response.SC_NOT_FOUND);

        } catch (BidAlreadyDoneException e) {
            JSONObject instance = new JSONObject();
            instance.put("status", 409);
            instance.put("message", e.getMessage());
            response.setStatus(response.SC_CONFLICT);
            PrintWriter out = response.getWriter();
            out.println(instance);
        }

        catch (ProjectAccessForbiddenException e){
            JSONObject instance = new JSONObject();
            instance.put("status", 403);
            instance.put("message", e.getMessage());
            response.setStatus(response.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            out.println(instance);
        }

    }
}
