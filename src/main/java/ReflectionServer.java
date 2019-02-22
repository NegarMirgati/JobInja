import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;


public class ReflectionServer {
    public void startServer() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8089), 0);
        server.createContext("/project", new ProjectsHandler());
        server.createContext("/project/", new ProjectHandler());
        server.setExecutor(null);
        server.start();
    }
}

