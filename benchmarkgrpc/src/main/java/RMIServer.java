import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIServer extends Remote{

    Long greeter(String name) throws RemoteException;

}
