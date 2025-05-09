package com.example.ecommercesystem.grpc;

import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import org.example.grpc.ErpServiceGrpc;
import org.example.grpc.OrderAck;
import org.example.grpc.OrderRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ErpClient {

    private static final Logger logger = Logger.getLogger(ErpClient.class.getName());

    private final ErpServiceGrpc.ErpServiceBlockingStub blockingStub;

    public ErpClient(ManagedChannel channel) {
        blockingStub = ErpServiceGrpc.newBlockingStub(channel);
    }

    public OrderAck sendOrder(String orderId, String productId, int quantity) {
        logger.info("Sende Bestellung an ERP: " + orderId);

        // Baue gRPC-Request
        OrderRequest request = OrderRequest.newBuilder()
                .setOrderID(orderId)
                .setProductID(productId)
                .setQuantity(quantity)
                .build();

        try {
            // ERP-Service aufrufen
            OrderAck ack = blockingStub.processOrder(request);
            logger.info("Antwort vom ERP-System erhalten");
            return ack;
        } catch (StatusRuntimeException e) {
            logger.log(Level.SEVERE, "gRPC-Aufruf fehlgeschlagen: {0}", e.getStatus());
            throw e;
        }
    }
}