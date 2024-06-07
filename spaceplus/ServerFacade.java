package spaceplus;

import spaceplus.configuration.ParseWebXML;
import spaceplus.configuration.ParsePublicXML;
import java.net.InetSocketAddress;

public class ServerFacade implements Runnable {
    private final InetSocketAddress address;
    
    public ServerFacade(int port) {
        address = new InetSocketAddress(port);
    }
    
    @Override
    public void run() {
        try {
            var parserWeb = new ParseWebXML();
            var parserPublic = new ParsePublicXML();
            var threadWeb = new Thread(parserWeb);
            var threadPublic = new Thread(parserPublic);
            threadPublic.start();
            threadWeb.start();

            var server = new Server(address);

            threadWeb.join();
            threadPublic.join();

            parserWeb.execute(server::addHandler);
            parserPublic.execute(server::makeStatic);

            parserPublic.execute((item) -> {
                System.out.println("static link: " + item);
            });

            parserWeb.execute((key, value) -> {
                System.out.println("key: " + key + " value: " + value);
            });

            new Thread(server).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
