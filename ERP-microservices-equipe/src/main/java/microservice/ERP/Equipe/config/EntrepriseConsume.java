package microservice.ERP.Equipe.config;

import lombok.extern.slf4j.Slf4j;
import microservice.ERP.Equipe.dto.EntrepriseDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EntrepriseConsume {
    @RabbitListener(queues = "entreprise.queue")
    public void handleProjectCreated(EntrepriseDTO entreprise) {
        System.out.println("Reçu projet : " + entreprise.getName());
        log.info("Reçu entreprise :==========>>>>>>> " + entreprise.getName());
    }
}
