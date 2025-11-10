package microservice.ERP.Equipe.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "equipe.exchange";
    public static final String ROUTING_KEY = "equipe.created";
    public static final String QUEUE = "equipe.queue";

    @Bean
    public TopicExchange equipeExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue equipeQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public Binding binding(Queue equipeQueue, TopicExchange equipeExchange) {
        return BindingBuilder.bind(equipeQueue).to(equipeExchange).with(ROUTING_KEY);
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
}
