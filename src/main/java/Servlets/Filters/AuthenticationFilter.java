package Servlets.Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import DataLayer.DataMappers.user.UserMapper;
import Entities.User;
import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            System.out.println("IN FILTER");
            HttpServletRequest httpRequest = (HttpServletRequest) req;
            String jwt = httpRequest.getHeader("authorization").substring(7);
            verifyJWT(jwt, req, resp);
            chain.doFilter(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            try {
                System.out.println("in null exception");
                HttpServletResponse res = (HttpServletResponse) resp;
                res.setStatus(res.SC_UNAUTHORIZED);
                req.setAttribute("username", null);
                chain.doFilter(req, resp);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ServletException e1) {
                    e1.printStackTrace();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    private void verifyJWT (String token, ServletRequest request, ServletResponse response) throws Exception{
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            Algorithm algorithm = Algorithm.HMAC256("joboonja");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            String username = jwt.getClaim("username").asString();
            UserMapper um = new UserMapper();
            User user = um.find(username);
            //res.addHeader("username", user.getUsername());
            request.setAttribute("username", user.getUsername());
            System.out.println("AUTORIZED!!!!!");
        } catch (JWTVerificationException exception){
            res.setStatus(res.SC_FORBIDDEN); // 403 error
        }
    }

}
