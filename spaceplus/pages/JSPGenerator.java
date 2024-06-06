package spaceplus.pages;

public class JSPGenerator {
    public String generate(String javasource, String classname) {
        return new StringBuilder()
            .append("package spaceplus.view;\n")
            .append("\n")
            .append("import spaceplus.Handler;\n")
            .append("import com.sun.net.httpserver.HttpExchange;\n")
            .append("\n")
            .append("public class ")
            .append(classname)
            .append(" extends Handler {\n")
            .append("    @Override \n")
            .append("    protected void doGet(HttpExchange exchange) {\n")
            .append("        try {\n")
            .append("            String response = \"")
            .append(javasource)
            .append("\";\n")
            .append("            exchange.sendResponseHeaders(200, response.length());\n")
            .append("            exchange.getResponseBody().write(response.getBytes());\n")
            .append("            exchange.getResponseBody().close();\n")
            .append("        } catch (Exception e) {\n")
            .append("            e.printStackTrace();\n")
            .append("        }\n")
            .append("    }\n")
            .append("}")
            .toString();
    }
}