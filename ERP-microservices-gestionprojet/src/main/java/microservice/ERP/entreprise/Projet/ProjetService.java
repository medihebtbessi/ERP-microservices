package microservice.ERP.entreprise.Projet;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjetService {

    private final ProjetRepo projetRepo;

    public Projet createProjet(Projet projet) {
        // statut par défaut
        projet.setStatus(StatusProjet.EN_ATTENTE);
        return projetRepo.save(projet);

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

}
