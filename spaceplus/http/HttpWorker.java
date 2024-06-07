package spaceplus.http;

import java.util.ResourceBundle;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public abstract class HttpWorker implements HttpHandler {

    private static ResourceBundle localString = ResourceBundle.getBundle("spaceplus.http.LocalStrings");

    @Override
    public void handle(HttpExchange exchange) {
        var request = new HttpRequest(exchange);
        var response = new HttpResponse(exchange);

        String requestMethod = exchange.getRequestMethod();
        switch (requestMethod) {
            case "GET":
                doGet(request, response);
                break;
            case "POST":
                doPost(request, response);
                break;
            case "DELETE":
                doDelete(request, response);
                break;
            case "PUT":
                doPut(request, response);
                break;
            default:
                String message = localString.getString("http.method_not_implemented");
                response.sendError(HttpResponse.SC_METHOD_NOT_ALLOWED, message);
                break;
        }
    }

    protected void doGet(HttpRequest request, HttpResponse response) {
        String protocol = request.getProtocol();
        String message = localString.getString("http.method_get_not_supported");

        if (protocol.endsWith("1.1"))
            response.sendError(HttpResponse.SC_METHOD_NOT_ALLOWED, message);
        else
            response.sendError(HttpResponse.SC_BAD_REQUEST, message);
    }

    protected void doPost(HttpRequest request, HttpResponse response) {
        String protocol = request.getProtocol();
        String message = localString.getString("http.method_post_not_supported");

        if (protocol.endsWith("1.1"))
            response.sendError(HttpResponse.SC_METHOD_NOT_ALLOWED, message);
        else
            response.sendError(HttpResponse.SC_BAD_REQUEST, message);
    }

    protected void doDelete(HttpRequest request, HttpResponse response) {
        String protocol = request.getProtocol();
        String message = localString.getString("http.method_delete_not_supported");

        if (protocol.endsWith("1.1"))
            response.sendError(HttpResponse.SC_METHOD_NOT_ALLOWED, message);
        else
            response.sendError(HttpResponse.SC_BAD_REQUEST, message);
    }

    protected void doPut(HttpRequest request, HttpResponse response) {
        String protocol = request.getProtocol();
        String message = localString.getString("http.method_put_not_supported");

        if (protocol.endsWith("1.1"))
            response.sendError(HttpResponse.SC_METHOD_NOT_ALLOWED, message);
        else
            response.sendError(HttpResponse.SC_BAD_REQUEST, message);
    }
}
