package Pages;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;

public class project implements IPage {

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException {
        HashMap<String, String> map =(HashMap<String, String>) httpExchange.getAttribute("content");
        if(map != null)
            System.out.println("xxxx ");
        else
            System.out.println("yyyy");
        String response = "<html><body><h1>" + map.get("data") +  "</h1></body></html>" ;
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}