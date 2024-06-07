package spaceplus.pages;

import java.io.File;
import java.io.FileReader;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.net.URI;

public class JSPFacade {
    private static final String WORKHOME = System.getenv("workdir");
    private static final ConcurrentMap<String, RequestDispatcher> cache = new ConcurrentHashMap<>();

    private final File file;
    public JSPFacade(String pathname) {
        file = new File(WORKHOME + pathname);

        if (!file.canRead()) {
            System.err.println("unable to read file" + file.getPath());
            throw new RuntimeException("unable to read file");
        }
    }

    public RequestDispatcher createJSP() {
        try {
            String classname, filename;
            filename = file.getName();
            filename = filename.substring(0, filename.length() - 4);
            classname = filename.substring(0, 1).toUpperCase() + filename.substring(1);

            var dispatcher = cache.get(classname);
            if (dispatcher != null)
                return dispatcher;

            String[] parse = new JSPParser().parseJSP(new FileReader(file));
            String source = new JSPGenerator().generate(parse[0], parse[1], classname);

            String pathpackage = "spaceplus/jsp/";
            String classpath = pathpackage + classname + ".java";

            var result = new JSPCompiler().compile(classpath, source);

            // update to log
            if (!result) {
                System.out.println("Compilation failed");
                return null;
            }

            System.out.println("Compilation successful");


            if (new File(WORKHOME + "/" + pathpackage + classname + ".class").exists()) {
                String target = WORKHOME + "/bin/" + pathpackage;
                var targetFile = new File(target);
                var sourcedir = Paths.get(WORKHOME + "/" + pathpackage);
                var targetdir = Paths.get(target);

                if (!targetFile.exists())
                    targetFile.mkdirs();

                var movefile = sourcedir.resolve(classname + ".class");

                Files.move(movefile, targetdir.resolve(movefile.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            }


            String javaclass = classpath.substring(0, classpath.length() - 5).replaceAll("/", ".");

            var constructor = Class.forName(javaclass).getDeclaredConstructor();
            var instance = (RequestDispatcher) constructor.newInstance();

            cache.put(classname, instance);

            return instance;
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}
