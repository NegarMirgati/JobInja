package Servlets;

import ContentProviders.userContentProvider;
import Entities.User;
import Repositories.UserRepo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "delSkill")
public class delSkill extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userID = request.getParameter("userID");
        String name = request.getParameter("name");
        System.out.println("userId: " +userID);
        System.out.println("name: " +name);
        //////
        User u = UserRepo.getUserById(userID);
        u.delSkill(name);
        /////
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> skills = new HashMap<String, String>();

        map = userContentProvider.getHTMLContentsForUser(userID);
        skills = userContentProvider.getUserSkills(userID);

        request.setAttribute("content", map);
        request.setAttribute("skills",skills);
        request.setAttribute("userID", userID);

        System.out.println(request.getAttribute("content"));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user.jsp");
        // System.out.println("ljksdfgjsdhfgkjsdhfgksdkfg"+request.getSession().getAttribute("status").toString());
        dispatcher.forward(request, response);

    }

}
