package spaceplus.pages;

import java.io.BufferedReader;
import java.io.FileReader;

public class JSPParser {
    public String parseJSP(String filePath) {
        var javasource = new StringBuilder();
        try {
            try (var reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("<%=")) {
                        int start = line.indexOf("<%=");
                        int end = line.indexOf("%>");
                        var expression = line.substring(start + 3, end).trim();
                        javasource.append("out.print(").append(expression).append(");\n");
                    } else {
                        javasource.append("out.print(\"")
                            .append(line.replace("\"", "\\\"")).append("\");\n");
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return javasource.toString();
    }
}