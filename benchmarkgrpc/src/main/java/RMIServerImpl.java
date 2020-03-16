import java.io.IOException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

public class RMIServerImpl extends UnicastRemoteObject implements RMIServer{

    private static final Logger logger = Logger.getLogger(RMIServerImpl.class.getName());

    private static int count = 0;

    static String getString(int n){
        String baseString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "0123456789" +
                "abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++){
            int index = (int) (baseString.length() * Math.random());
            sb.append(baseString.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public String greeter(String name) {

        long cur_time = System.nanoTime();

        int length = (int)Math.pow(2, (6 + count % 50000));
        String st = getString(length);

        return st;
    }

    public static final String MESSAGE = "Hello World";

    public RMIServerImpl() throws RemoteException {
        super(0); // required to avoid the 'rmic' step, see below
    }

    public String getMessage() {
        return MESSAGE;
    }

    public static void main(String args[]) throws Exception {
        System.out.println("RMI server started");

        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099);
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }

        //Instantiate RmiServer
        RMIServerImpl server = new RMIServerImpl();

        // Bind this object instance to the name "RmiServer"
        Naming.rebind("//localhost/RmiServer", server);
        System.out.println("PeerServer bound in registry");
    }
}
