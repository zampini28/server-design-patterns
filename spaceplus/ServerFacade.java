package spaceplus;

import spaceplus.configuration.ParseWebXML;
import java.net.InetSocketAddress;

public class ServerFacade implements Runnable {
    private int port;
    
    public ServerFacade(int port) {
        this.port = port;
    }
    
    @Override
    public void run() {
        try {
            var parser = new ParseWebXML();
            var thread = new Thread(parser);
            thread.start();

            var address = new InetSocketAddress(port);
            var server = new Server(address);

            thread.join();
            parser.execute(server::addHandler);

            new Thread(server).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}