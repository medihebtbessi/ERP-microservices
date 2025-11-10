package microservice.ERP.Financiere.services;

import microservice.ERP.Financiere.client.EntrepriseClient;
import microservice.ERP.Financiere.client.dto.DevisResponse;
import microservice.ERP.Financiere.client.dto.EntrepriseResponse;
import microservice.ERP.Financiere.event.FinancePublisher;
import microservice.ERP.Financiere.model.Devis;
import microservice.ERP.Financiere.repository.DevisRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DevisService {

  private final DevisRepository devisRepository;
  private final EntrepriseClient entrepriseClient;
  private final FinancePublisher financePublisher;

  public DevisService(DevisRepository devisRepository, EntrepriseClient entrepriseClient, FinancePublisher financePublisher) {
    this.devisRepository = devisRepository;
    this.entrepriseClient = entrepriseClient;
    this.financePublisher = financePublisher;
  }

    public Page<DevisResponse> getAll(Pageable pageable) {
        return devisRepository.findAll(pageable).map(devis -> {
            //EntrepriseResponse entreprise = entrepriseClient.getById(devis.getIdEntreprise());

            DevisResponse dto = new DevisResponse();
            dto.setIdDevis(devis.getIdDevis());
            dto.setReferenceDevis(devis.getReferenceDevis());
            dto.setMontantTotal(devis.getMontantTotal());
            dto.setDateCreation(devis.getDateCreation());
            dto.setDateExpiration(devis.getDateExpiration());
            dto.setStatutDevis(devis.getStatutDevis().name());
            dto.setEntrepriseName("test");
            return dto;
        });
    }
  public Optional<Devis> getById(Long id) {
    return devisRepository.findById(id);
  }

    public DevisResponse create(Devis devis) {

        EntrepriseResponse entreprise = entrepriseClient.getById(devis.getIdEntreprise());
        if (entreprise == null) {
            throw new RuntimeException("Entreprise introuvable !");
        }

        Devis saved = devisRepository.save(devis);

        financePublisher.publishDevisCreated(saved);

        // ✅ Mapper vers DTO
        DevisResponse dto = new DevisResponse();
        dto.setIdDevis(saved.getIdDevis());
        dto.setReferenceDevis(saved.getReferenceDevis());
        dto.setMontantTotal(saved.getMontantTotal());
        dto.setDateCreation(saved.getDateCreation());
        dto.setDateExpiration(saved.getDateExpiration());
        dto.setStatutDevis(saved.getStatutDevis().name());
        dto.setEntrepriseName(entreprise.getName());

        return dto;
    }

  public Devis update(Long id, Devis devis) {
    Devis existing = devisRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Devis non trouvé"));

    existing.setReferenceDevis(devis.getReferenceDevis());
    existing.setDateCreation(devis.getDateCreation());
    existing.setDateExpiration(devis.getDateExpiration());
    existing.setMontantTotal(devis.getMontantTotal());
    existing.setStatutDevis(devis.getStatutDevis());

    Devis updated = devisRepository.save(existing);

    financePublisher.publishDevisCreated(updated);
    System.out.println("📤 Événement envoyé à RabbitMQ : devis.updated -> " + updated.getReferenceDevis());

    return updated;
  }

  public void delete(Long id) {
    devisRepository.deleteById(id);
    System.out.println("🗑 Devis supprimé avec ID " + id);
  }

  // 🔹 Test Feign
  public EntrepriseResponse testCommunication(Long id) {
    var entreprise = entrepriseClient.getById(id);
    System.out.println("Test communication Feign réussi : " + entreprise);
    return entreprise;
  }
}
