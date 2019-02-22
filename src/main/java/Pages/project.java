package Pages;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import com.sun.net.httpserver.HttpExchange;

public class project implements IPage {

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException {
        HashMap<String, String> map =(HashMap<String, String>) httpExchange.getAttribute("content");
        String response = "<html><body><h1>";
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            response += "<li>" +  pair.getKey() + " : " + pair.getValue() + "</li>";
            it.remove(); // avoids a ConcurrentModificationException
        }
        response +=   "</h1></body></html>" ;
        System.out.println("end!!");
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}