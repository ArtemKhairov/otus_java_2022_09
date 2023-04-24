package ru.otus.server;

import io.grpc.stub.StreamObserver;
import ru.otus.protobuf.generated.NumberRequest;
import ru.otus.protobuf.generated.NumberResponse;
import ru.otus.protobuf.generated.RemoteNumberServiceGrpc;


import static ru.otus.common.GRPCNumberUtil.sleep;

public class RemoteNumberServiceImpl extends RemoteNumberServiceGrpc.RemoteNumberServiceImplBase {

@Override
public void generateNumbers(NumberRequest request, StreamObserver<NumberResponse> responseObserver) {
    long currentValue = 0;

    System.out.println(String.format("Generate number from %d to %d", request.getNumberFrom(), request.getNumberTo()));

    for (long i = request.getNumberFrom(); i <= request.getNumberTo(); i++) {
        currentValue = i;

        NumberResponse response = NumberResponse.newBuilder().setValue(currentValue).build();
        responseObserver.onNext(response);

        sleep(2);
    }

    responseObserver.onCompleted();

    System.out.println("number generation finished");
}
}
