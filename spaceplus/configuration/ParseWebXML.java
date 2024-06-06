package spaceplus.configuration;

import com.sun.net.httpserver.HttpHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;

public class ParseWebXML implements Runnable {
    private final String filename;
    private final Map<String, HttpHandler> map = new HashMap<>();

    public ParseWebXML() {
        this("");
    }
    public ParseWebXML(String pathname) {
        filename = pathname + "web.xml";
    }

    @Override
    public void run() {
        try {
            var doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filename);
            doc.getDocumentElement().normalize();

            var nodelist = doc.getElementsByTagName("server");

            for (int i = 0; i < nodelist.getLength(); i++) 
            {
                var element = (Element) nodelist.item(i);
                if (element.getNodeType() != Node.ELEMENT_NODE) continue;

                var url = element.getElementsByTagName("url-pattern").item(0).getTextContent();
                var classname = element.getElementsByTagName("class-name").item(0).getTextContent();
                System.out.println(url + " " + classname);

                var instance = (HttpHandler) Class.forName(classname).getDeclaredConstructor().newInstance();
                map.put(url, instance);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void execute(BiConsumer<String, HttpHandler> action) {
        map.forEach(action);
    }
}