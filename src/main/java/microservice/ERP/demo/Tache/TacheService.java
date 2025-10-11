package microservice.ERP.demo.Tache;


import lombok.RequiredArgsConstructor;
import microservice.ERP.demo.Projet.Projet;
import microservice.ERP.demo.Projet.ProjetRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TacheService {
    private final TacheRepo tacheRepo;
    private final ProjetRepo projetRepo;

    public Tache createTache(Long projetId, Tache tache) {
        Projet projet = projetRepo  .findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet non trouvé"));
        tache.setProjet(projet);
        tache.setStatus(StatusTache.A_FAIRE); // statut par défaut
        return tacheRepo.save(tache);
    }

    public List<Tache> getTachesByProjet(Long projetId) {
        return tacheRepo.findByProjetId(projetId);
    }

    public Tache updateTache(Long id, Tache tache) {
        Tache existing = tacheRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));

        existing.setTitre(tache.getTitre());
        existing.setDescription(tache.getDescription());
        existing.setDateDebut(tache.getDateDebut());
        existing.setDateFin(tache.getDateFin());
        existing.setStatus(tache.getStatus());
        existing.setPriorite(tache.getPriorite());

        return tacheRepo.save(existing);
    }

    public void deleteTache(Long id) {
        tacheRepo.deleteById(id);
    }
}
