package com.example.ecommercesystem.grpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;

@Configuration
public class GrpcClientConfig {
    @Bean
    public ManagedChannel erpChannel() {
        return Grpc.newChannelBuilder("localhost:50051", InsecureChannelCredentials.create())
                .build();
    }

    @Bean
    public ErpClient erpClient(ManagedChannel erpChannel) {
        return new ErpClient(erpChannel);
    }
}
