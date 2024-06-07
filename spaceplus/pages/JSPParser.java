package spaceplus.pages;

import java.io.BufferedReader;
import java.io.FileReader;

public class JSPParser {
    public String[] parseJSP(FileReader fileReader) {
        var javasource = new StringBuilder();
        var imports = new StringBuilder();
        try {
            try (var reader = new BufferedReader(fileReader)) {
                String line;
                while ((line = reader.readLine()) != null) 
                {
                    if (line.contains("<%=")) 
                    {
                        int start = line.indexOf("<%=");
                        int end = line.indexOf("%>");

                        String begin = line.substring(0, start);
                        String endl = line.substring(end + 2, line.length());

                        var expression = "String.valueOf(" + line.substring(start + 3, end).trim() + ").getBytes()";

                        javasource.append("out.write(\"").append(begin.replace("\"", "\\\"")).append("\".getBytes());\n")
                        .append("out.write(").append(expression).append(");\n")
                        .append("out.write(\"").append(endl.replace("\"", "\\\"")).append("\".getBytes());\n"); 
                    } 

                    else if (line.contains("<%@")) 
                    {
                        if (!line.contains("import")) continue;
                        int start = line.indexOf("import=\"") + 8;
                        int end = line.indexOf("\"", start);
                        var expression = line.substring(start, end).trim();
                        imports.append("import ").append(expression).append(";\n");
                    } 

                    else if (line.contains("<%")) {
                        int start = line.indexOf("<%");
                        int end = line.indexOf("%>");

                        var begin = line.substring(0, start);
                        var expression = line.substring(start + 2, end).trim();
                        var endl = line.substring(end + 2, line.length());

                        javasource.append("out.write(\"").append(begin.replace("\"", "\\\"")).append("\".getBytes());\n")
                        .append(expression)
                        .append("out.write(\"").append(endl.replace("\"", "\\\"")).append("\".getBytes());\n"); 
                    } 

                    else {
                        javasource.append("out.write(\"").append(line.replace("\"", "\\\"")).append("\".getBytes());\n");
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return new String[]{ imports.toString(), javasource.toString() };
    }
}
