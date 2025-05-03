package logservice.logservice;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public TopicExchange logExchange() {
        return new TopicExchange("log.exchange");
    }

    @Bean
    public Queue logQueue() {
        return new Queue("log.queue");
    }

    @Bean
    public Binding logBinding(Queue logQueue, TopicExchange logExchange) {
        return BindingBuilder.bind(logQueue).to(logExchange).with("log.routing.key");
    }
}