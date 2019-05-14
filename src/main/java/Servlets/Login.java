package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;
import java.io.*;

import Entities.User;
import DataLayer.DataMappers.user.UserMapper;
import utils.HashGenerator;

@WebServlet(  name = "Login",  urlPatterns = { "/login"} , initParams = {
        @WebInitParam(name = "id" , value = "Not provided"),
        @WebInitParam(name = "password" , value = "Not provided")} )
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");
        try{
            UserMapper um = new UserMapper();
            User u = um.find(id);
            String hashedPassword = u.getHashedPassword();
            String salt = u.getSalt();
            if(HashGenerator.passwordMatches(hashedPassword, password, salt)){
                System.out.println("hahahahahahahahahahahah");
                String jwt = generateJWTToken(id, password);
                JSONObject userJWT = new JSONObject();
                userJWT.put("jwt", jwt);
                PrintWriter out = response.getWriter();
                out.println(userJWT);
            }
            else
                response.setStatus(response.SC_FORBIDDEN);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    private String generateJWTToken(String username, String password) {
        String token = "";
        try {
                Algorithm algorithmHS = Algorithm.HMAC256("joboonja");
                token = JWT.create()
                    .withClaim("username", username)
                    .withClaim("password", password)
                    .withIssuer("auth0")
                    .sign(algorithmHS);
            return token;
       // } catch (UnsupportedEncodingException exception) {
            //UTF-8 encoding not supported
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }
}
