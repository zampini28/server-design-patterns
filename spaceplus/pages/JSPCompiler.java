package spaceplus.pages;

import java.io.FileWriter;

import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class JSPCompiler {
    public boolean compile(String classpath, String source) {
        try {
            try (var writer = new FileWriter(classpath)) {
                writer.write(source);
            }

            Iterable<String> options = Arrays.asList(new String[]{ "-d", "root/bird/server/bin" });

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            var task = compiler.getTask(null, null, null, options, null, null);

            int result = compiler.run(null, null, null, classpath);
            return result == 0;
        } 
        catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}
