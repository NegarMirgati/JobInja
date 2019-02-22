import Pages.IPage;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ProjectsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            System.out.println(httpExchange.getRequestURI().getPath());
            StringTokenizer tokenizer = new StringTokenizer(httpExchange.getRequestURI().getPath(), "/");
            String page = tokenizer.nextToken();
            System.out.println(page);
            Class<IPage> pageClass;
            try {
                pageClass = (Class<IPage>) Class.forName("Pages." +page);
                IPage newInstance = pageClass.getDeclaredConstructor().newInstance();
                HashMap<String, String> map = new HashMap<>();
                map.put("data", "salam");
                httpExchange.setAttribute("content", map);
                newInstance.HandleRequest(httpExchange);
            } catch (ClassNotFoundException |
                    InstantiationException |
                    IllegalAccessException |
                    IllegalArgumentException |
                    InvocationTargetException |
                    NoSuchMethodException |
                    SecurityException e) {
                e.printStackTrace();
                String response =
                        "<html>"
                                + "<body>Page \""+ page + "\" not found.</body>"
                                + "</html>";
                httpExchange.sendResponseHeaders(404, response.length());
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
}


