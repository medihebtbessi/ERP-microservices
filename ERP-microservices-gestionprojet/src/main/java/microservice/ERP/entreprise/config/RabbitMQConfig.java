package microservice.ERP.entreprise.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE = "project.exchange";
    public static final String ROUTING_KEY = "project.created";

    @Bean
    public TopicExchange projectExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue projectQueue() {
        return new Queue("project.queue", true);
    }

    @Bean
    public Binding binding(Queue projectQueue, TopicExchange projectExchange) {
        return BindingBuilder.bind(projectQueue).to(projectExchange).with(ROUTING_KEY);
    }
}
