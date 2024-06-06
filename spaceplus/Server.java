package spaceplus;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private final HttpServer server;
    public Server(InetSocketAddress address) {
        try {
            server = HttpServer.create(address, -1);

            var pool = Executors.newCachedThreadPool();
            server.setExecutor(pool);

            Runtime.getRuntime().addShutdownHook(new Thread(this::close));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addHandler(String url, HttpHandler handler) {
        server.createContext(url, handler);
    }

    @Override
    public void run() {
        server.start();
        System.out.println("Server started on port " + server.getAddress().getPort());
    }

    public void close() {
        server.stop(0);
        System.out.println("\tShutdown Server...");
    }
}
