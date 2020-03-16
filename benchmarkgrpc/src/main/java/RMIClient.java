import java.rmi.Naming;

public class RMIClient {

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

    public static void main(String args[]) throws Exception {
        long time = 0;
        int count = 0;
        long first = 0;
        long last = 0;
        int length = 32;

        while (length <= 262145) {
            length = length *2;
            while (count <= 50000) {
                long cur_time = System.nanoTime();
                RMIServer server = (RMIServer) Naming.lookup("//10.70.20.119/RmiServer");


                String str = getString(length);

                String toServer = server.greeter(str);
                long toClient = System.nanoTime();
//                if (count % 5000 == 0) {
//                    System.out.println("Counts: " + count);
//                    System.out.println("Total time is: " + (toClient - cur_time));
//                    System.out.println("Time to Server: " + (toServer - cur_time));
//                    System.out.println("Time to Client: " + (toClient - toServer));
//                }
                long total = toClient - cur_time;

                if (count < 5000) {
                    first += total;
                }

                if (count > 45000) {
                    last += total;
                }

                time += total;
                count += 1;
            }
            System.out.println("\n Length is: " + length);
            System.out.println("Average time: " + (time / count));
            System.out.println("Average time first 5000: " + (first / 5000));
            System.out.println("Average time last 5000: " + (last / 5000));

            time = 0;
            count = 0;
            first = 0;
            last = 0;

        }
    }
}