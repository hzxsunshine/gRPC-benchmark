import com.google.protobuf.ByteString;
import com.google.protobuf.Message;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import pathstore.grpc.PathStoreServiceGrpc;
import pathstore.grpc.PathStoreServiceGrpc.*;
import pathstore.grpc.pathStoreProto;
import pathstore.grpc.pathStoreProto.HelloReply;
import pathstore.grpc.pathStoreProto.HelloRequest;


import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GRPCClient {
    private static final Logger logger = Logger.getLogger(GRPCClient.class.getName());

    private final ManagedChannel channel;
    private final PathStoreServiceBlockingStub blockingStub;
    private static int counter = 0;

    // not used for now.
    private final PathStoreServiceStub asyncStub;

    public GRPCClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext());
    }

    /** Construct client for accessing RouteGuide server using the existing channel. */
    public GRPCClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = PathStoreServiceGrpc.newBlockingStub(channel);
        asyncStub = PathStoreServiceGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

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

    /** Say hello to server. */
    public long greet(String name) {
        //logger.info("Will try to greet " + name + " ...");
        long cur_time = System.nanoTime();
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        long total_time;
        counter += 1;
        try {
            response = blockingStub.sayHello(request);

            String time_1 = response.getBackinfo();
            long time_2 = System.nanoTime();

//            if (counter % 5000 == 0) {
//                System.out.println("Counts: " + counter);
//                System.out.println("Total time is :" + (time_2 - cur_time));
//                System.out.println("Time from Client to Server is: " + (time_1 - cur_time));
//                System.out.println("Time from Server to Client is: " + (time_2 - time_1));
//            }
            total_time = time_2 - cur_time;
            //logger.info("Greeting: " + response.getMessage());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            total_time = 0;
        }
        return total_time;
    }

    public static void main(String[] args) throws Exception {
        // Access a service running on the local machine on port 50051
        GRPCClient client = new GRPCClient("10.70.20.119", 8960);

        long time = 0;
        int count = 0;
        long first = 0;
        long total = 0;
        long last = 0;

        int length = 16;
            try {
                while (length <= 262145) {
                length = length * 2;
                while (count < 50000) {
                    String str = getString(length);

                    if (args.length > 0) {
                        str = args[0];
                    }
                    total = client.greet(str);
                    if (count < 5000) {
                        first += total;
                    }

                    if (count > 45000) {
                        last += total;
                    }

                    time += total;
                    count += 1;
                    }
                    System.out.println("\nLength is: " + length);
                    System.out.println("Average time: " + (time / count));
                    System.out.println("Average time first 5000: " + (first / 5000));
                    System.out.println("Average time last 5000: " + (last / 5000));

                    time = 0;
                    count = 0;
                    first = 0;
                    last = 0;
                }
            } finally {
                client.shutdown();
            }
    }

}
