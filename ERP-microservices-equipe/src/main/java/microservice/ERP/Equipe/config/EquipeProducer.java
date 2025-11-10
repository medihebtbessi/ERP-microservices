package microservice.ERP.Equipe.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EquipeProducer {

    private final RabbitTemplate rabbitTemplate;

    public EquipeProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendProjectCreatedEvent(EquipeResponse equipe) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                equipe
        );
        System.out.println("✅ Event envoyé : " + equipe.nom());
    }
}
