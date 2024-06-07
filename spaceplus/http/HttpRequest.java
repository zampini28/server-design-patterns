package spaceplus.http;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import spaceplus.pages.JSPFacade;
import spaceplus.pages.RequestDispatcher;

public class HttpRequest {
    private final HttpExchange exchange;

    private Map<String, String> queryParams = null;

    public HttpRequest(HttpExchange exchange) {
        this.exchange = exchange;

        String query = exchange.getRequestURI().getQuery();

        if (query != null)
            queryParams = Arrays.stream(query.split("&"))
                .map(params -> params.split("="))
                .filter(params -> params.length == 2)
                .collect(Collectors.toMap(params -> params[0], params -> params[1]));
    }

    /* getProtocol */
    public String getProtocol() {
        return exchange.getProtocol();
    }

    /* getParameter */
    public String getParameter(String name) {
        if (queryParams == null)
            return null;
        return queryParams.get(name);
    }

    /* getAttribute */
    public Object getAttribute(String name) {
        return exchange.getAttribute(name);
    }

    /* setAttribute */
    public void setAttribute(String name, Object object) {
        exchange.setAttribute(name, object);
    }

    /* getRequestDispather */
    public RequestDispatcher getRequestDispatcher(String path) {
        return new JSPFacade(path).createJSP();
    }

    /* getReader */
    public BufferedReader getReader() {
        InputStream inputstream = exchange.getRequestBody();
        return new BufferedReader(new InputStreamReader(inputstream));
    }
}
