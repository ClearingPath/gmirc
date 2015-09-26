package gMIRC;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class grpcMIRCGrpc {

  // Static method descriptors that strictly reflect the proto.
  public static final io.grpc.MethodDescriptor<gMIRC.UserChannel,
      gMIRC.ResCode> METHOD_JOIN =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "gMIRC.grpcMIRC", "Join",
          io.grpc.protobuf.ProtoUtils.marshaller(gMIRC.UserChannel.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(gMIRC.ResCode.parser()));
  public static final io.grpc.MethodDescriptor<gMIRC.Username,
      gMIRC.ResCode> METHOD_REG_USER =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "gMIRC.grpcMIRC", "RegUser",
          io.grpc.protobuf.ProtoUtils.marshaller(gMIRC.Username.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(gMIRC.ResCode.parser()));
  public static final io.grpc.MethodDescriptor<gMIRC.UserChannel,
      gMIRC.ResCode> METHOD_LEAVE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "gMIRC.grpcMIRC", "Leave",
          io.grpc.protobuf.ProtoUtils.marshaller(gMIRC.UserChannel.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(gMIRC.ResCode.parser()));
  public static final io.grpc.MethodDescriptor<gMIRC.Username,
      gMIRC.ResCode> METHOD_EXIT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "gMIRC.grpcMIRC", "Exit",
          io.grpc.protobuf.ProtoUtils.marshaller(gMIRC.Username.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(gMIRC.ResCode.parser()));
  public static final io.grpc.MethodDescriptor<gMIRC.UserMessage,
      gMIRC.ResCode> METHOD_MESSAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "gMIRC.grpcMIRC", "Message",
          io.grpc.protobuf.ProtoUtils.marshaller(gMIRC.UserMessage.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(gMIRC.ResCode.parser()));
  public static final io.grpc.MethodDescriptor<gMIRC.Username,
      gMIRC.UpdateString> METHOD_REGULAR_UPDATES =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "gMIRC.grpcMIRC", "RegularUpdates",
          io.grpc.protobuf.ProtoUtils.marshaller(gMIRC.Username.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(gMIRC.UpdateString.parser()));

  public static grpcMIRCStub newStub(io.grpc.Channel channel) {
    return new grpcMIRCStub(channel);
  }

  public static grpcMIRCBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new grpcMIRCBlockingStub(channel);
  }

  public static grpcMIRCFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new grpcMIRCFutureStub(channel);
  }

  public static interface grpcMIRC {

    public void join(gMIRC.UserChannel request,
        io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver);

    public void regUser(gMIRC.Username request,
        io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver);

    public void leave(gMIRC.UserChannel request,
        io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver);

    public void exit(gMIRC.Username request,
        io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver);

    public void message(gMIRC.UserMessage request,
        io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver);

    public void regularUpdates(gMIRC.Username request,
        io.grpc.stub.StreamObserver<gMIRC.UpdateString> responseObserver);
  }

  public static interface grpcMIRCBlockingClient {

    public gMIRC.ResCode join(gMIRC.UserChannel request);

    public gMIRC.ResCode regUser(gMIRC.Username request);

    public gMIRC.ResCode leave(gMIRC.UserChannel request);

    public gMIRC.ResCode exit(gMIRC.Username request);

    public gMIRC.ResCode message(gMIRC.UserMessage request);

    public gMIRC.UpdateString regularUpdates(gMIRC.Username request);
  }

  public static interface grpcMIRCFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<gMIRC.ResCode> join(
        gMIRC.UserChannel request);

    public com.google.common.util.concurrent.ListenableFuture<gMIRC.ResCode> regUser(
        gMIRC.Username request);

    public com.google.common.util.concurrent.ListenableFuture<gMIRC.ResCode> leave(
        gMIRC.UserChannel request);

    public com.google.common.util.concurrent.ListenableFuture<gMIRC.ResCode> exit(
        gMIRC.Username request);

    public com.google.common.util.concurrent.ListenableFuture<gMIRC.ResCode> message(
        gMIRC.UserMessage request);

    public com.google.common.util.concurrent.ListenableFuture<gMIRC.UpdateString> regularUpdates(
        gMIRC.Username request);
  }

  public static class grpcMIRCStub extends io.grpc.stub.AbstractStub<grpcMIRCStub>
      implements grpcMIRC {
    private grpcMIRCStub(io.grpc.Channel channel) {
      super(channel);
    }

    private grpcMIRCStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected grpcMIRCStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new grpcMIRCStub(channel, callOptions);
    }

    @java.lang.Override
    public void join(gMIRC.UserChannel request,
        io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_JOIN, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void regUser(gMIRC.Username request,
        io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_REG_USER, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void leave(gMIRC.UserChannel request,
        io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_LEAVE, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void exit(gMIRC.Username request,
        io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void message(gMIRC.UserMessage request,
        io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_MESSAGE, callOptions), request, responseObserver);
    }

    @java.lang.Override
    public void regularUpdates(gMIRC.Username request,
        io.grpc.stub.StreamObserver<gMIRC.UpdateString> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_REGULAR_UPDATES, callOptions), request, responseObserver);
    }
  }

  public static class grpcMIRCBlockingStub extends io.grpc.stub.AbstractStub<grpcMIRCBlockingStub>
      implements grpcMIRCBlockingClient {
    private grpcMIRCBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private grpcMIRCBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected grpcMIRCBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new grpcMIRCBlockingStub(channel, callOptions);
    }

    @java.lang.Override
    public gMIRC.ResCode join(gMIRC.UserChannel request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_JOIN, callOptions), request);
    }

    @java.lang.Override
    public gMIRC.ResCode regUser(gMIRC.Username request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_REG_USER, callOptions), request);
    }

    @java.lang.Override
    public gMIRC.ResCode leave(gMIRC.UserChannel request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_LEAVE, callOptions), request);
    }

    @java.lang.Override
    public gMIRC.ResCode exit(gMIRC.Username request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request);
    }

    @java.lang.Override
    public gMIRC.ResCode message(gMIRC.UserMessage request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_MESSAGE, callOptions), request);
    }

    @java.lang.Override
    public gMIRC.UpdateString regularUpdates(gMIRC.Username request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_REGULAR_UPDATES, callOptions), request);
    }
  }

  public static class grpcMIRCFutureStub extends io.grpc.stub.AbstractStub<grpcMIRCFutureStub>
      implements grpcMIRCFutureClient {
    private grpcMIRCFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private grpcMIRCFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected grpcMIRCFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new grpcMIRCFutureStub(channel, callOptions);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<gMIRC.ResCode> join(
        gMIRC.UserChannel request) {
      return futureUnaryCall(
          channel.newCall(METHOD_JOIN, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<gMIRC.ResCode> regUser(
        gMIRC.Username request) {
      return futureUnaryCall(
          channel.newCall(METHOD_REG_USER, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<gMIRC.ResCode> leave(
        gMIRC.UserChannel request) {
      return futureUnaryCall(
          channel.newCall(METHOD_LEAVE, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<gMIRC.ResCode> exit(
        gMIRC.Username request) {
      return futureUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<gMIRC.ResCode> message(
        gMIRC.UserMessage request) {
      return futureUnaryCall(
          channel.newCall(METHOD_MESSAGE, callOptions), request);
    }

    @java.lang.Override
    public com.google.common.util.concurrent.ListenableFuture<gMIRC.UpdateString> regularUpdates(
        gMIRC.Username request) {
      return futureUnaryCall(
          channel.newCall(METHOD_REGULAR_UPDATES, callOptions), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final grpcMIRC serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder("gMIRC.grpcMIRC")
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_JOIN,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                gMIRC.UserChannel,
                gMIRC.ResCode>() {
              @java.lang.Override
              public void invoke(
                  gMIRC.UserChannel request,
                  io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver) {
                serviceImpl.join(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_REG_USER,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                gMIRC.Username,
                gMIRC.ResCode>() {
              @java.lang.Override
              public void invoke(
                  gMIRC.Username request,
                  io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver) {
                serviceImpl.regUser(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_LEAVE,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                gMIRC.UserChannel,
                gMIRC.ResCode>() {
              @java.lang.Override
              public void invoke(
                  gMIRC.UserChannel request,
                  io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver) {
                serviceImpl.leave(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_EXIT,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                gMIRC.Username,
                gMIRC.ResCode>() {
              @java.lang.Override
              public void invoke(
                  gMIRC.Username request,
                  io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver) {
                serviceImpl.exit(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_MESSAGE,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                gMIRC.UserMessage,
                gMIRC.ResCode>() {
              @java.lang.Override
              public void invoke(
                  gMIRC.UserMessage request,
                  io.grpc.stub.StreamObserver<gMIRC.ResCode> responseObserver) {
                serviceImpl.message(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_REGULAR_UPDATES,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                gMIRC.Username,
                gMIRC.UpdateString>() {
              @java.lang.Override
              public void invoke(
                  gMIRC.Username request,
                  io.grpc.stub.StreamObserver<gMIRC.UpdateString> responseObserver) {
                serviceImpl.regularUpdates(request, responseObserver);
              }
            }))).build();
  }
}
