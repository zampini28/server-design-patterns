package spaceplus.pages;

import java.io.FileWriter;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class JSPCompiler {
    public boolean compile(String classname, String source) {
        try {
            var fileName = classname + ".java";
            try (var writer = new FileWriter(fileName)) {
                writer.write(source);
            }

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            int result = compiler.run(null, null, null, fileName);
            return result == 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}