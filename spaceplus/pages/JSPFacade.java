package spaceplus.pages;

import java.util.Random;

public class JSPFacade {
    private String filePath;
    public JSPFacade(String filePath) {
        this.filePath = filePath;
    }

    public void dispatch() {
        String source, classname = generateRandomLetters(12);

        source = new JSPParser().parseJSP(filePath);
        source = new JSPGenerator().generate(source, classname);

        var result = new JSPCompiler().compile(classname, source);

        if (result) System.out.println("Compilation successful");
        else System.out.println("Compilation failed");
    }

    public String generateRandomLetters(int length) {
        var random = new Random();
        var str = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            str.append((char) ('A' + random.nextInt(26)));
        return str.toString();
    }
}