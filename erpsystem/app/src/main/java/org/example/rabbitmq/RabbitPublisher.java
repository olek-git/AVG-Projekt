package org.example.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitPublisher {
    private static final String QUEUE_NAME = "order_updates";

    public static void sendOrderUpdate(String orderID, String deliveryStatus) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            try (Connection connection = factory.newConnection();
                    Channel channel = connection.createChannel()) {

                channel.queueDeclare(QUEUE_NAME, true, false, false, null);

                String message = String.format(
                        "{\"orderID\":\"%s\", \"deliveryStatus\":\"%s\"}",
                        orderID, deliveryStatus);

                Map<String, Object> headers = new HashMap<>();
                headers.put("__TypeId__", "com.example.ecommercesystem.dto.StatusUpdateMessage");

                AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                    .contentType("application/json")
                    .headers(headers)
                    .build();

                channel.basicPublish("", QUEUE_NAME, props, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
