package Pages;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.io.OutputStreamWriter;
import Entities.*;
import com.sun.net.httpserver.HttpExchange;

public class projects implements IPage {

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException {
        String encoding = "ISO-8859-1";
        HashMap<String, HashMap<String, String>> map = (HashMap<String, HashMap<String, String>>) httpExchange.getAttribute("content");
        String response = "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Project</title>\n" +
                "<style>\n" +
                "table {" +
                "text-align: center;" +
                "margin: 0 auto;" +
                "}"+
                "td {"+
                "min-width: 300px;" +
                "margin: 5px 5px 5px 5px;" +
                "text-align: center;" +
                "}" +
                "</style>" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "<tr>\n" +
                "<th>id</th>\n" +
                "<th>title</th>\n" +
                "<th>budget</th>\n" +
                "</tr>\n";

        for (HashMap.Entry<String, HashMap<String,String>> entry : map.entrySet()) {
            String key = entry.getKey();
            HashMap<String, String> value = (HashMap<String, String>) entry.getValue();
            String projectId = value.get("id");
            String title = value.get("title");
            String budget = value.get("budget");
            response += "<tr>" +
                        "<td>" + new String(projectId.getBytes(), encoding) + "</td>\n" +
                        "<td>" + new String(title.getBytes(), encoding)+ "</td>\n" +
                        "<td>" + new String(budget.getBytes(), encoding) + "</td>\n" +
                        "</tr>";
        }
        response +=  "</table>"
                + "</body>"
                + "</html>";

        httpExchange.sendResponseHeaders(200, response.length());
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=" + encoding);
        OutputStreamWriter os = new OutputStreamWriter(httpExchange.getResponseBody(), encoding);
        os.write(response);
        os.close();
    }

}