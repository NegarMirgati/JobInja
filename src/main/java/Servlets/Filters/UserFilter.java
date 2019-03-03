package Servlets.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.StringTokenizer;

@WebFilter(filterName = "UserFilter")
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String path = ((HttpServletRequest)req).getRequestURI();
        System.out.println(path);
        StringTokenizer tokenizer = new StringTokenizer(path, "/");
        String context = tokenizer.nextToken();
        String projectID = tokenizer.nextToken();
        System.out.println(projectID);
        req.setAttribute("userID", projectID);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/");
        dispatcher.forward(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}