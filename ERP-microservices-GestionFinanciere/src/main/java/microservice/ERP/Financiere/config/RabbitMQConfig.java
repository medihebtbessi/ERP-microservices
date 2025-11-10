package microservice.ERP.Financiere.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  public static final String EXCHANGE = "finance.exchange";

  // Files (queues)
  public static final String QUEUE_DEVIS = "devis.queue";
  public static final String QUEUE_FACTURE = "facture.queue";

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(EXCHANGE);
  }

  @Bean
  public Queue devisQueue() {
    return new Queue(QUEUE_DEVIS, true);
  }

  @Bean
  public Queue factureQueue() {
    return new Queue(QUEUE_FACTURE, true);
  }

  @Bean
  public Binding bindingDevis(Queue devisQueue, TopicExchange exchange) {
    return BindingBuilder.bind(devisQueue).to(exchange).with("devis.*");
  }

  @Bean
  public Binding bindingFacture(Queue factureQueue, TopicExchange exchange) {
    return BindingBuilder.bind(factureQueue).to(exchange).with("facture.*");
  }
  @Bean
  public Jackson2JsonMessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                       Jackson2JsonMessageConverter jsonMessageConverter) {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(jsonMessageConverter);
    return template;
  }
}
