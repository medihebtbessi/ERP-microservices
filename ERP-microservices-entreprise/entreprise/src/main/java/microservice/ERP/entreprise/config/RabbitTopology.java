package microservice.ERP.entreprise.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTopology {

    public static final String EXCHANGE = "entreprise.exchange";
    public static final String ROUTING_KEY = "entreprise.created";
    public static final String QUEUE = "entreprise.queue";

    @Bean
    public TopicExchange entrepriseExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue entrepriseQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public Binding binding(Queue entrepriseQueue, TopicExchange entrepriseExchange) {
        return BindingBuilder.bind(entrepriseQueue).to(entrepriseExchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}

