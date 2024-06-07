package spaceplus.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;

public class ParsePublicXML implements Runnable {
    private static final String WORKHOME = System.getenv("workdir");
    private final String filename;
    private final List<String> list = new ArrayList<>();

    public ParsePublicXML() { this(""); }
    public ParsePublicXML(String pathname) {
        filename = pathname + "public.xml";
    }

    @Override
    public void run() {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            var builder = factory.newDocumentBuilder();
            var doc = builder.parse(filename);

            doc.getDocumentElement().normalize();

            var nodelist = doc.getElementsByTagName("files-list");
            for (int i = 0; i < nodelist.getLength(); i++) 
            {
                var element = (Element) nodelist.item(i);
                if (element.getNodeType() != Node.ELEMENT_NODE) continue;

                var filePathNodes = element.getElementsByTagName("file-path");
                for (int j = 0; j < filePathNodes.getLength(); j++) {
                    var filePathElement = (Element) filePathNodes.item(j);
                    var filepath = WORKHOME + filePathElement.getTextContent();
                    list.add(filepath);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void execute(Consumer<String> action) {
        list.forEach(action);
    }
}
