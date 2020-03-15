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

    /** Say hello to server. */
    public long greet(String name) {
        logger.info("Will try to greet " + name + " ...");
        long cur_time = System.nanoTime();
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        long total_time;
        try {
            response = blockingStub.sayHello(request);

            long time_1 = response.getBack();
            long time_2 = System.nanoTime();

            System.out.println("Total time is :" + (time_2 - cur_time));
            System.out.println("Time from Client to Server is: " + (time_1 - cur_time));
            System.out.println("Time from Server to Client is: " + (time_2 - time_1));
            total_time = time_2 - cur_time;
            logger.info("Greeting: " + response.getMessage());
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            total_time = 0;
        }
        return total_time;
    }

    public static void main(String[] args) throws Exception {
        // Access a service running on the local machine on port 50051
        GRPCClient client = new GRPCClient("10.20.70.119", 8980);

        long time = 0;
        int count = 0;
        try {
            while (count <= 10) {
                String user = "GRPC_test";
                // Use the arg as the name to greet if provided
                if (args.length > 0) {
                    user = args[0];
                }
                time += client.greet(user);
                count += 1;

            }
        } finally {
            System.out.println("Average total time: " + (time/count));
            client.shutdown();
        }
    }

}
