package ru.otus.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

import static ru.otus.common.GRPCAppConst.SERVER_PORT;

public class NumberServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Number server is starting");

        Server server = ServerBuilder
                .forPort(SERVER_PORT)
                .addService(new RemoteNumberServiceImpl())
                .build();

        server.start();
        System.out.println("Server as awaiting for connections");
        server.awaitTermination();
    }
}
