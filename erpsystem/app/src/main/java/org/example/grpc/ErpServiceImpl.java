package org.example.grpc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.example.rabbitmq.RabbitPublisher;

import com.google.protobuf.Empty;

import io.grpc.stub.StreamObserver;

public class ErpServiceImpl extends ErpServiceGrpc.ErpServiceImplBase {
    // Implementiere die Methoden hier
    // Beispiel: public void getProduct(GetProductRequest request,
    // StreamObserver<GetProductResponse> responseObserver) {
    // responseObserver.onNext(response);
    // responseObserver.onCompleted();
    //
    @Override
    public void processOrder(OrderRequest request, StreamObserver<OrderAck> responseObserver) {
        System.out.println("Bestellung erhalten: " + request.getOrderID());

        // TODO: Bestand aktualisieren mit getQuantity()

        // synchron ans E-Commerce-System zurücksenden
        String deliveryDate = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String deliveryStatus = "Pending";

        // gRPC-Antwort zurückgeben
        OrderAck ack = OrderAck.newBuilder()
                .setDeliveryDate(deliveryDate)
                .setDeliveryStatus(deliveryStatus)
                .build();

        responseObserver.onNext(ack);
        responseObserver.onCompleted();
    }

    @Override
    public void updateOrderStatus(UpdateStatusRequest request, StreamObserver<Empty> responseObserver) {
        String orderId = request.getOrderId();
        String deliveryStatus = request.getNewStatus();

        RabbitPublisher.sendOrderUpdate(orderId, deliveryStatus);

        responseObserver.onNext(Empty.getDefaultInstance());
        responseObserver.onCompleted();
    }
}
