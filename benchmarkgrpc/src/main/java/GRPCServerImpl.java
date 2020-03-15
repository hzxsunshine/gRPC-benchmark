import com.google.rpc.Status;
import com.google.protobuf.ByteString;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import pathstore.grpc.pathStoreProto;
import pathstore.grpc.pathStoreProto.*;
import pathstore.grpc.PathStoreServiceGrpc;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GRPCServerImpl {

    private static final Logger logger = Logger.getLogger(GrpcService.class.getName());

    private final int port;
    private final Server server;

    /** Create a PathStore server using serverBuilder as a base. */
    public GRPCServerImpl(int port) throws IOException {
        this.port = port;
        server = ServerBuilder.forPort(port).addService(new GrpcService()).build();
        //this(ServerBuilder.forPort(port), port);
    }

    /** Start serving requests. */
    public void start() throws IOException {
        server.start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                GRPCServerImpl.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    /** Stop serving requests and shutdown resources. */
    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final GRPCServerImpl server = new GRPCServerImpl(8980);
        server.start();
        server.blockUntilShutdown();
    }

    private static class GrpcService extends PathStoreServiceGrpc.PathStoreServiceImplBase {

        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
            long cur_time = System.nanoTime();
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + request.getName())
                                                        .setBack(cur_time).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }

}
