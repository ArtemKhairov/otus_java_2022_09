package ru.otus.client;

import io.grpc.ManagedChannelBuilder;
import ru.otus.protobuf.generated.NumberRequest;
import ru.otus.protobuf.generated.RemoteNumberServiceGrpc;

import static ru.otus.common.GRPCAppConst.*;
import static ru.otus.common.GRPCNumberUtil.sleep;

public class NumberClient {
    private long currentValue = 0;

    public static void main(String[] args) {
        System.out.println(String.format("Creating connection to %s on port:%d", SERVER_HOST, SERVER_PORT));
        var channel = ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();

        var remoteService = RemoteNumberServiceGrpc.newStub(channel);

        System.out.println("Start client");
        new NumberClient().runClientJob(remoteService);

        System.out.println("Shutdown client");
        channel.shutdown();
    }

    private void runClientJob(RemoteNumberServiceGrpc.RemoteNumberServiceStub remoteService) {
        var request = NumberRequest.newBuilder().setNumberFrom(SERVER_VALUE_FROM).setNumberTo(SERVER_VALUE_TO).build();
        var observer = new NumberStreamObserver();
        remoteService.generateNumbers(request, observer);

        for (long i = CLIENT_VALUE_FROM; i <= CLIENT_VALUE_TO; i++) {
//            System.out.println("number in iteration: " + i);
            System.out.println("current Value:" + calculateCurrentValue(observer));
            sleep(1);
        }
    }

    private long calculateCurrentValue(NumberStreamObserver observer) {
        var lastValue = observer.getLastAndReset();
        currentValue += lastValue + 1;
        return currentValue;
    }

}
