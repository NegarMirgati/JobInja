import Exceptions.ProjectNotFoundException;
import Exceptions.ProjectAccessForbiddenException;
import ContentProviders.*;
import Pages.IPage;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ProjectHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        //System.out.println(httpExchange.getRequestURI().getPath());
        StringTokenizer tokenizer = new StringTokenizer(httpExchange.getRequestURI().getPath(), "/");
        int count = tokenizer.countTokens();
        String page = tokenizer.nextToken();
        String projectId = (tokenizer.nextToken());
        Class<IPage> pageClass;
        try {
            if (count != 2){
                throw new IllegalArgumentException();
            }

            pageClass = (Class<IPage>) Class.forName("Pages." +page);
            IPage newInstance = pageClass.getDeclaredConstructor().newInstance();
       //     HashMap<String, String> map = projectContentProvider.getHTMLContentsForProject("1", projectId);
            ProjectContentProvider.checkAccess("1",projectId);
       //     httpExchange.setAttribute("content", map);
            newInstance.HandleRequest(httpExchange);
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                IllegalArgumentException |
                InvocationTargetException |
                NoSuchMethodException |
                SecurityException |
                SQLException |
                ProjectNotFoundException e) {
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
        catch (ProjectAccessForbiddenException e) {
            e.printStackTrace();
            String response =
                    "<html>"
                            + "<body>Page \""+ page + "\" access forbidden.</body>"
                            + "</html>";
            httpExchange.sendResponseHeaders(403, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
