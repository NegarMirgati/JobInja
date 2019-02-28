package Pages;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.io.OutputStreamWriter;


import com.sun.net.httpserver.HttpExchange;

public class user implements IPage {

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException {
        String encoding = "ISO-8859-1";
        HashMap<String, String> map = (HashMap<String, String>) httpExchange.getAttribute("content");
        String response = "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>User</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <ul>\n";
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String value = (String) pair.getValue();
            String str = new String(value.getBytes(), encoding);
            response += "<li>" +  pair.getKey() + " : " + str + "</li>";
            it.remove();
        }
        response +=  "</ul>"
                + "</body>"
                + "</html>";

        httpExchange.sendResponseHeaders(200, response.length());
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=" + encoding);
        OutputStreamWriter os = new OutputStreamWriter(httpExchange.getResponseBody(), encoding);
        os.write(response);
        os.close();
    }

}