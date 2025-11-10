package microservice.ERP.Financiere.services;

import microservice.ERP.Financiere.client.EntrepriseClient;
import microservice.ERP.Financiere.client.dto.FactureResponse;
import microservice.ERP.Financiere.event.FinancePublisher;
import microservice.ERP.Financiere.model.Facture;
import microservice.ERP.Financiere.repository.DevisRepository;
import microservice.ERP.Financiere.repository.FactureRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FactureService {

  private final FactureRepository factureRepository;
  private final DevisRepository devisRepository;
  private final EntrepriseClient entrepriseClient;
  private final FinancePublisher financePublisher;

  public FactureService(
    FactureRepository factureRepository,
    DevisRepository devisRepository,
    EntrepriseClient entrepriseClient,
    FinancePublisher financePublisher) {
    this.factureRepository = factureRepository;
    this.devisRepository = devisRepository;
    this.entrepriseClient = entrepriseClient;
    this.financePublisher = financePublisher;
  }

    public Page<FactureResponse> getAll(Pageable pageable) {
        return factureRepository.findAll(pageable).map(facture -> {
            var entreprise = entrepriseClient.getById(facture.getIdEntreprise());
            var devis = facture.getDevis();

            FactureResponse dto = new FactureResponse();
            dto.setIdFacture(facture.getIdFacture());
            dto.setReferenceFacture(facture.getReferenceFacture());
            dto.setDateEmission(facture.getDateEmission());
            dto.setDateEcheance(facture.getDateEcheance());
            dto.setMontantTotal(facture.getMontantTotal());
            dto.setStatutFacture(facture.getStatutFacture().name());
            dto.setEntrepriseName(entreprise.getName());
            dto.setReferenceDevis(devis.getReferenceDevis());

            return dto;
        });
    }
  public Optional<Facture> getById(Long id) {
    return factureRepository.findById(id);
  }

    public FactureResponse create(Facture facture) {
        if (facture.getDevis() == null || facture.getDevis().getIdDevis() == null) {
            throw new RuntimeException("Aucun devis associé !");
        }

        // ✅ Charger le devis depuis la DB
        var devis = devisRepository.findById(facture.getDevis().getIdDevis())
                .orElseThrow(() -> new RuntimeException("Devis introuvable !"));

        // ✅ Récupérer l'entreprise du devis
        facture.setIdEntreprise(devis.getIdEntreprise());

        var entreprise = entrepriseClient.getById(devis.getIdEntreprise());
        if (entreprise == null) {
            throw new RuntimeException("Entreprise introuvable !");
        }

        System.out.println("✅ Entreprise : " + entreprise.getName());
        System.out.println("✅ Devis : " + devis.getReferenceDevis());

        // ✅ Sauvegarde facture
        Facture saved = factureRepository.save(facture);

        financePublisher.publishFactureCreated(saved);
        System.out.println("📤 Événement envoyé à RabbitMQ : facture.created -> " + saved.getReferenceFacture());

        FactureResponse dto = new FactureResponse();
        dto.setIdFacture(saved.getIdFacture());
        dto.setReferenceFacture(saved.getReferenceFacture());
        dto.setDateEmission(saved.getDateEmission());
        dto.setDateEcheance(saved.getDateEcheance());
        dto.setMontantTotal(saved.getMontantTotal());
        dto.setStatutFacture(saved.getStatutFacture().name());
        dto.setEntrepriseName(entreprise.getName());
        dto.setReferenceDevis(devis.getReferenceDevis());

        return dto;
    }
  public Facture update(Long id, Facture facture) {
    facture.setIdFacture(id);
    Facture updated = factureRepository.save(facture);

    financePublisher.publishFactureCreated(updated);
    System.out.println("📤 Événement envoyé à RabbitMQ : facture.updated -> " + updated.getReferenceFacture());

    return updated;
  }

  public void delete(Long id) {
    factureRepository.deleteById(id);
    System.out.println("🗑️ Facture supprimée avec ID " + id);
  }
}
