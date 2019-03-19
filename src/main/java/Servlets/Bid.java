package Servlets;

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
        response.setStatus(response.SC_NOT_IMPLEMENTED);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bidAmount = request.getParameter("amount");
        String projectID = request.getParameter("id");
        PrintWriter out = response.getWriter();
        JSONObject instance = new JSONObject();

        try {
            BidCommand bidCommand = new BidCommand(projectID, Integer.valueOf(bidAmount), "1");
            response.setContentType("application/json;charset=UTF-8");
            projectContentProvider.hasBadeForProject("1", projectID);
            projectContentProvider.checkAccess("1", projectID);

            if(!bidCommand.bidIsPossible()){
                response.setStatus(response.SC_BAD_REQUEST);
                JSONObject status = new JSONObject();
                status.put("status", "Invalid bid amount");
                out.println(status);
            }
            else{
                bidCommand.execute();
                response.setStatus(response.SC_OK);
                JSONObject status = new JSONObject();
                status.put("status", "bid successfully done.");
                out.println(status);

            }
        } catch (UserNotFoundException | ProjectNotFoundException e) {
            printApiOutputError(e, 404,response);

        } catch (BidAlreadyDoneException e) {
            printApiOutputError(e, 409,response);
        }

        catch (ProjectAccessForbiddenException e){
            printApiOutputError(e, 403,response);
        }

        catch(NumberFormatException e){
            instance.put("status", 422);
            instance.put("message", "Invalid parameter type exception");
            response.setStatus(422);
            out.println(instance);
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
