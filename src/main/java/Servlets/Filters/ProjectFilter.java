package Servlets.Filters;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;


@WebFilter(filterName = "ProjectFilter")
public class ProjectFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        String path = ((HttpServletRequest)req).getRequestURI();
        StringTokenizer tokenizer = new StringTokenizer(path, "/");
        String context = tokenizer.nextToken();
        if(tokenizer.hasMoreTokens()) {
            String projectID = tokenizer.nextToken();
            System.out.println(projectID);
            req.setAttribute("projectID", projectID);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/project/");
            dispatcher.forward(req, resp);
        }
        else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("/project");
            dispatcher.forward(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

}