import Pages.IPage;
import ContentProviders.*;

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
            StringTokenizer tokenizer = new StringTokenizer(httpExchange.getRequestURI().getPath(), "/");
            int count = tokenizer.countTokens();
            String page = tokenizer.nextToken();

            Class<IPage> pageClass;
            try {
                if (count != 1 ){
                    throw new IllegalArgumentException();
                }
                pageClass = (Class<IPage>) Class.forName("Pages." +page + "s");
                IPage newInstance = pageClass.getDeclaredConstructor().newInstance();
                //HashMap<String, HashMap<String, String>> map = projectContentProvider.getHTMLContentsForAllProjects("1");
               // System.out.println(map);
              //  httpExchange.setAttribute("content", map);
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


