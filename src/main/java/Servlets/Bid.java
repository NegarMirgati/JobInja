package Servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import Commands.*;
import Exceptions.UserNotFoundException;

@WebServlet(name = "Bid")
public class Bid extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bidAmount = request.getParameter("bidAmount");
        String projectID = request.getParameter("projectID");
        BidCommand bidCommand = new BidCommand(projectID, Integer.valueOf(bidAmount), "1");
        try {
            if(bidCommand.bidIsPossible()){
                bidCommand.execute();
                request.setAttribute("status", "Your bid has successfully submited!!!");
                request.setAttribute("color", "green");
            }
            else{
                request.setAttribute("status", "Failed to submit your bid!!!");
                request.setAttribute("color", "red");
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/status.jsp");
        dispatcher.forward(request, response);

    }
}
