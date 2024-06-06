package spaceplus;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) {
        try {
            String requestMethod = exchange.getRequestMethod();
            switch (requestMethod) {
                case "GET" -> doGet(exchange);
                case "POST"-> doPost(exchange);
                default -> exchange.sendResponseHeaders(405, -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected abstract void doGet(HttpExchange exchange);
    protected abstract void doPost(HttpExchange exchange);
}