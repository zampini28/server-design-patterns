package spaceplus;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;

public class Server implements Runnable {
    private static final String WORKHOME = System.getenv("workdir");
    private final HttpServer server;

    public Server(InetSocketAddress address) {
        try {
            server = HttpServer.create(address, -1);

            var pool = Executors.newCachedThreadPool();
            server.setExecutor(pool);

            var runtime = Runtime.getRuntime();
            runtime.addShutdownHook(new Thread(this::close));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addHandler(String url, HttpHandler handler) {
        server.createContext(url, handler);
    }

    public void makeStatic(String filepath) {
        String relativePath = filepath.substring(WORKHOME.length()).replace(File.separator, "/");

        if (!relativePath.startsWith("/")) 
            relativePath = "/" + relativePath;

        server.createContext(relativePath, new HttpHandler(){
            @Override
            public void handle(HttpExchange exchange) {
                try {
                    var file = new File(filepath);
                    if (file.exists() && !file.isDirectory()) {
                        byte[] content = Files.readAllBytes(file.toPath());
                        exchange.sendResponseHeaders(200, content.length);
                        try (OutputStream os = exchange.getResponseBody()) {
                            os.write(content);
                        }
                    }
                } catch (Exception e) { e.printStackTrace(); }
            }
        });
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
