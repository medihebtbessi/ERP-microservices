package microservice.ERP.entreprise.config;

import microservice.ERP.entreprise.Projet.Projet;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProjectProducer {
    private final RabbitTemplate rabbitTemplate;

    public ProjectProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendProjectCreatedEvent(Projet project) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                project
        );
        System.out.println("✅ Event envoyé : " + project.getNom());
    }
}
