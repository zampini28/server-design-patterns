package spaceplus.pages;

public class JSPGenerator {
    public String generate(String imports, String javasource, String classname) {
        return new StringBuilder()
            .append("package spaceplus.jsp;\n")
            .append("\n")
            .append("import spaceplus.http.HttpRequest;\n")
            .append("import spaceplus.http.HttpResponse;\n")
            .append("import spaceplus.pages.RequestDispatcher;\n")
            .append(imports)
            .append("\n")
            .append("public class ")
            .append(classname)
            .append(" implements RequestDispatcher {\n")
            .append("    @Override \n")
            .append("    public void forward(HttpRequest request, HttpResponse response) {\n")
            .append("        try {\n")
            .append("           response.sendResponseHeaders(200, 0);\n")
            .append("           response.setContentType(\"text/html\");\n")
            .append("           var out = response.getResponseBody();\n")
            .append(javasource)
            .append("           response.getResponseBody().close();\n")
            .append("        } catch (Exception e) { e.printStackTrace(); }\n")
            .append("    }\n")
            .append("}")
            .toString();
    }
}
