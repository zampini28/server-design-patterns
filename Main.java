import spaceplus.ServerFacade;

public class Main {
    public static void main(String[] args) {
        int port = 80;

        if (args.length != 0)
            port = Integer.parseInt(args[0]);
        else
            System.out.println("using default port (80)");
        
        var serverFacade = new ServerFacade(port);
        new Thread(serverFacade).start();
    }
}
