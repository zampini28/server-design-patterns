package spaceplus.http;

import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;

public class HttpResponse {
    public static final int SC_NO_CONTENT = 204;
    public static final int SC_BAD_REQUEST = 400;
    public static final int SC_UNAUTHORIZED = 401;
    public static final int SC_PRECONDITION_FAILED = 412;
    public static final int SC_INTERNAL_SERVER_ERROR = 500;
    public static final int SC_METHOD_NOT_ALLOWED = 405;

    private final HttpExchange exchange;

    private String contentType = null;
    private String characterEncoding = null;

    public HttpResponse(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public void updateHeaders() {
        if (contentType == null) return;

        var hearders = exchange.getResponseHeaders();
        if (characterEncoding == null)
            hearders.set("Content-Type", contentType);
        else
            hearders.set("Content-Type", contentType + "; charset=" + characterEncoding);
    }

    /* void setContentType(String type) */
    public void setContentType(String type) {
        contentType = type;
        updateHeaders();
    }

    /* void setCharacterEncoding(String charset) */
    public void setCharacterEncoding(String charset) {
        characterEncoding = charset;
        updateHeaders();
    }

    /* void sendRedirect(String location) */
    public void sendRedirect(String location) {
        var hearders = exchange.getResponseHeaders();
        hearders.set("Location", location);
        sendResponseHeaders(302, 0);
    }

    /* void setStatus(int sc) */
    public void sendResponseHeaders(int sc) {
        sendResponseHeaders(sc, 0);
    }

    public void sendError(int sc, String message) {
        try {
            setContentType("text/plain");
            setCharacterEncoding("UTF-8");
            sendResponseHeaders(sc, message.getBytes("UTF-8").length);
            var os = getResponseBody();
            os.write(message.getBytes("UTF-8"));
            os.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    /* void sendResponseHeaders(int rcode, long responseLength) */
    public void sendResponseHeaders(int rcode, long responseLength) {
        try {
            exchange.sendResponseHeaders(rcode, responseLength);
        } catch (Exception e) { e.printStackTrace(); }
    }

    /* OutputStream getResponseBody() */
    public OutputStream getResponseBody() {
        return exchange.getResponseBody();
    }
}
