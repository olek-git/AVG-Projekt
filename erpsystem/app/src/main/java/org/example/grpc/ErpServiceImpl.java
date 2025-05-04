package org.example.grpc;

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

        // TODO: Hier die Logik für Lieferdatum und -status implementieren, die direkt
        // synchron ans ERP-System gesendet werden.
        // Beispielwerte für Lieferdatum und -status
        String deliveryDate = "2025-05-05";
        String orderStatus = "Processed";

        // gRPC-Antwort zurückgeben
        OrderAck ack = OrderAck.newBuilder()
                .setDeliveryDate(deliveryDate)
                .setOrderStatus(orderStatus)
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
