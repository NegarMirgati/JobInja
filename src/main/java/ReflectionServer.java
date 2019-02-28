import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;


public class ReflectionServer {
    public void startServer() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8091), 0);
        server.createContext("/project", new ProjectsHandler());
        server.createContext("/project/", new ProjectHandler());
        server.createContext("/user/", new UserHandler());
        server.setExecutor(null);
        server.start();
    }
}

