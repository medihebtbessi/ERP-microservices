package microservice.ERP.entreprise.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EquipeConsume {
    @RabbitListener(queues = "equipe.queue")
    public void handleProjectCreated(EquipeResponse equipe) {
        System.out.println("Reçu projet : " + equipe.nom());
        log.info("Reçu projet :==========>>>>>>> " + equipe.nom());
    }
}
