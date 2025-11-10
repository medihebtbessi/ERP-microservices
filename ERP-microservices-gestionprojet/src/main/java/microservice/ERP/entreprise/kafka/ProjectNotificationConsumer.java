package microservice.ERP.entreprise.kafka;

import lombok.RequiredArgsConstructor;
import microservice.ERP.entreprise.Projet.ProjetService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectNotificationConsumer {

    private final SimpMessagingTemplate messagingTemplate;
    private final ProjetService projectNotificationService;

    @KafkaListener(topics = "project-alert", groupId = "notification-group")
    public void consumeProjectAlert(String message) {
        System.out.println("📩 Alerte projet reçue : " + message);

        // 🧠 Stocker en mémoire
        projectNotificationService.addAlert(message);

        // 🔔 Tentative d'envoi via WebSocket (facultatif)
        try {
            messagingTemplate.convertAndSend("/topic/alerts", message);
        } catch (Exception e) {
            System.out.println("⚠️ WebSocket non disponible, alerte enregistrée uniquement");
        }
    }
}
