package pathstore.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.26.0)",
    comments = "Source: PathStore.proto")
public final class PathStoreServiceGrpc {

  private PathStoreServiceGrpc() {}

  public static final String SERVICE_NAME = "pathstore.PathStoreService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pathstore.grpc.pathStoreProto.HelloRequest,
      pathstore.grpc.pathStoreProto.HelloReply> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHello",
      requestType = pathstore.grpc.pathStoreProto.HelloRequest.class,
      responseType = pathstore.grpc.pathStoreProto.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pathstore.grpc.pathStoreProto.HelloRequest,
      pathstore.grpc.pathStoreProto.HelloReply> getSayHelloMethod() {
    io.grpc.MethodDescriptor<pathstore.grpc.pathStoreProto.HelloRequest, pathstore.grpc.pathStoreProto.HelloReply> getSayHelloMethod;
    if ((getSayHelloMethod = PathStoreServiceGrpc.getSayHelloMethod) == null) {
      synchronized (PathStoreServiceGrpc.class) {
        if ((getSayHelloMethod = PathStoreServiceGrpc.getSayHelloMethod) == null) {
          PathStoreServiceGrpc.getSayHelloMethod = getSayHelloMethod =
              io.grpc.MethodDescriptor.<pathstore.grpc.pathStoreProto.HelloRequest, pathstore.grpc.pathStoreProto.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pathstore.grpc.pathStoreProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pathstore.grpc.pathStoreProto.HelloReply.getDefaultInstance()))
              .setSchemaDescriptor(new PathStoreServiceMethodDescriptorSupplier("SayHello"))
              .build();
        }
      }
    }
    return getSayHelloMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PathStoreServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PathStoreServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PathStoreServiceStub>() {
        @java.lang.Override
        public PathStoreServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PathStoreServiceStub(channel, callOptions);
        }
      };
    return PathStoreServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PathStoreServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PathStoreServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PathStoreServiceBlockingStub>() {
        @java.lang.Override
        public PathStoreServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PathStoreServiceBlockingStub(channel, callOptions);
        }
      };
    return PathStoreServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PathStoreServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PathStoreServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PathStoreServiceFutureStub>() {
        @java.lang.Override
        public PathStoreServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PathStoreServiceFutureStub(channel, callOptions);
        }
      };
    return PathStoreServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class PathStoreServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * chile will call addUserCommandEntry
     * </pre>
     */
    public void sayHello(pathstore.grpc.pathStoreProto.HelloRequest request,
        io.grpc.stub.StreamObserver<pathstore.grpc.pathStoreProto.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSayHelloMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                pathstore.grpc.pathStoreProto.HelloRequest,
                pathstore.grpc.pathStoreProto.HelloReply>(
                  this, METHODID_SAY_HELLO)))
          .build();
    }
  }

  /**
   */
  public static final class PathStoreServiceStub extends io.grpc.stub.AbstractAsyncStub<PathStoreServiceStub> {
    private PathStoreServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PathStoreServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PathStoreServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * chile will call addUserCommandEntry
     * </pre>
     */
    public void sayHello(pathstore.grpc.pathStoreProto.HelloRequest request,
        io.grpc.stub.StreamObserver<pathstore.grpc.pathStoreProto.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class PathStoreServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<PathStoreServiceBlockingStub> {
    private PathStoreServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PathStoreServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PathStoreServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * chile will call addUserCommandEntry
     * </pre>
     */
    public pathstore.grpc.pathStoreProto.HelloReply sayHello(pathstore.grpc.pathStoreProto.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class PathStoreServiceFutureStub extends io.grpc.stub.AbstractFutureStub<PathStoreServiceFutureStub> {
    private PathStoreServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PathStoreServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PathStoreServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * chile will call addUserCommandEntry
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<pathstore.grpc.pathStoreProto.HelloReply> sayHello(
        pathstore.grpc.pathStoreProto.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PathStoreServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PathStoreServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((pathstore.grpc.pathStoreProto.HelloRequest) request,
              (io.grpc.stub.StreamObserver<pathstore.grpc.pathStoreProto.HelloReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PathStoreServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PathStoreServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pathstore.grpc.pathStoreProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PathStoreService");
    }
  }

  private static final class PathStoreServiceFileDescriptorSupplier
      extends PathStoreServiceBaseDescriptorSupplier {
    PathStoreServiceFileDescriptorSupplier() {}
  }

  private static final class PathStoreServiceMethodDescriptorSupplier
      extends PathStoreServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PathStoreServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PathStoreServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PathStoreServiceFileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .build();
        }
      }
    }
    return result;
  }
}
