package microservice.ERP.entreprise.Projet;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import microservice.ERP.entreprise.config.ProjectProducer;
import microservice.ERP.entreprise.equipe.EquipeClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetService {

    private final ProjetRepo projetRepo;
    private final ProjectProducer projectProducer;
    private  final EquipeClient equipeClient;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Projet createProjet(Projet projet) {
        // statut par défaut
        var equipe=equipeClient.findEquipeById(projet.getIdEquipe()).orElseThrow(()->new EntityNotFoundException("Equipe non touve"));
        projet.setStatus(StatusProjet.EN_ATTENTE);
        projet=projetRepo.save(projet);
        ProjectResponse projetResponse = new ProjectResponse(projet.getId(), projet.getNom());
        projectProducer.sendProjectCreatedEvent(projetResponse);
        return projet;

    }

    public List<Projet> getAllProjets() {
        return projetRepo.findAll();
    }

    public Projet getProjetById(Long id) {
        return projetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));
    }

    public Projet updateProjet(Long id, Projet projet) {
        Projet existing = getProjetById(id);
        existing.setNom(projet.getNom());
        existing.setDescription(projet.getDescription());
        existing.setDateDebut(projet.getDateDebut());
        existing.setDateFin(projet.getDateFin());
        existing.setStatus(projet.getStatus());
        return projetRepo.save(existing);
    }

    public void deleteProjet(Long id) {
        projetRepo.deleteById(id);
    }

    @Scheduled(cron = "0 * * * * *")
    public void verifyProjectByDate() {
        List<Projet> projects = projetRepo.findAll();

        for (Projet projet : projects) {
            if (projet.getDateFin() != null && projet.getDateFin().isBefore(LocalDate.now())) {
                String message = String.format(
                        "🚨 Projet en retard : %s (date fin : %s, aujourd’hui : %s)",
                        projet.getNom(),
                        projet.getDateFin(),
                        LocalDate.now()
                );

                kafkaTemplate.send("project-alert", message);
                System.out.println("📤 Notification envoyée à Kafka : " + message);
            }
        }
    }


    private final List<String> projectAlerts = Collections.synchronizedList(new LinkedList<>());
    private static final int MAX_SIZE = 100;

    public void addAlert(String message) {
        synchronized (projectAlerts) {
            projectAlerts.add(0, message); // ajoute en haut
            if (projectAlerts.size() > MAX_SIZE) {
                projectAlerts.remove(projectAlerts.size() - 1);
            }
        }
    }

    public List<String> getAlerts() {
        return projectAlerts;
    }
}
