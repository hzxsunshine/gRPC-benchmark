import java.rmi.Naming;

public class RMIClient {

    public static void main(String args[]) throws Exception {
        long time = 0;
        int count = 0;
        while (count <= 10) {
            long cur_time = System.nanoTime();
            RMIServer server = (RMIServer) Naming.lookup("//127.0.0.1/RmiServer");
            long toServer = server.greeter("name");
            long toClient = System.nanoTime();
            System.out.println("Total time is: " + (toClient - cur_time));
            System.out.println("Time to Server: " + (toServer - cur_time));
            System.out.println("Time to Client: " + (toClient - toServer));
            long total = toClient - cur_time;
            time += total;
            count += 1;
        }
        System.out.println("Average time over 10 times: "+ (time / count));
    }
}