package ru.otus.client;

import io.grpc.stub.StreamObserver;
import ru.otus.protobuf.generated.NumberResponse;

public class NumberStreamObserver implements StreamObserver<NumberResponse> {

    private long value = 0;

    @Override
    public void onNext(NumberResponse valueFromServer) {
        System.out.println("Got value from server: " + setValue(valueFromServer.getValue()));
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("Catch error");
        e.printStackTrace();
    }

    @Override
    public void onCompleted() {
        System.out.println("Completed");
    }

    public synchronized long getLastAndReset() {
        long returnedVal = this.value;
        this.value = 0;
        return returnedVal;
    }

    private synchronized long setValue(long newValue){
        this.value = newValue;
        return value;
    }
}
