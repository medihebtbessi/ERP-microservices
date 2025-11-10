package microservice.ERP.entreprise.config;

import lombok.RequiredArgsConstructor;
import microservice.ERP.entreprise.Entreprise;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Entrepriseproducer {
    private final RabbitTemplate rabbitTemplate;




    public void sendProjectCreatedEvent(Entreprise  entreprise) {
        rabbitTemplate.convertAndSend(
                RabbitTopology.EXCHANGE,
                RabbitTopology.ROUTING_KEY,
                entreprise
        );
        System.out.println(" Event envoyé : " + entreprise.getName());
    }
}
