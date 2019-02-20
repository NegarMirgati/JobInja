package Pages;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public class ProjectsPage implements IPage {

    @Override
    public void HandleRequest(HttpExchange httpExchange) throws IOException {
        String response =
                "<html>"
                        + "<body><h1>This Is The First Page!</h1></body>"
                        + "</html>";
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}