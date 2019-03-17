import Exceptions.ProjectNotFoundException;
import Exceptions.UserNotFoundException;
import Pages.IPage;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import ContentProviders.*;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.StringTokenizer;

class UserHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(httpExchange.getRequestURI().getPath(), "/");
        int count = tokenizer.countTokens();
        String page = tokenizer.nextToken();
        String userId = (tokenizer.nextToken());
        Class<IPage> pageClass;
        try {
            if (count != 2){
                throw new IllegalArgumentException();
            }

            pageClass = (Class<IPage>) Class.forName("Pages." +page);
            IPage newInstance = pageClass.getDeclaredConstructor().newInstance();
          //  HashMap<String, String> map = userContentProvider.getHTMLContentsForUser(userId);
            //System.out.println(map);
         //   httpExchange.setAttribute("content", map);
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
        } /*catch (UserNotFoundException e) {
            e.printStackTrace();
        }*/
    }
}