package microservice.ERP.Financiere.services;

import microservice.ERP.Financiere.model.Devis;
import microservice.ERP.Financiere.repository.DevisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DevisService {

  @Autowired
  private  DevisRepository devisRepository;


  public Page<Devis> getAll(Pageable pageable) {
    return devisRepository.findAll(pageable);
  }

  public Optional<Devis> getById(Long id) {
    return devisRepository.findById(id);
  }

  public Devis create(Devis devis) {
    return devisRepository.save(devis);
  }

  public Devis update(Long id, Devis devis) {
    Devis existing = devisRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Devis non trouvé"));

    existing.setReferenceDevis(devis.getReferenceDevis());
    existing.setDateCreation(devis.getDateCreation());
    existing.setDateExpiration(devis.getDateExpiration());
    existing.setMontantTotal(devis.getMontantTotal());
    existing.setStatutDevis(devis.getStatutDevis());

    return devisRepository.save(existing);
  }


  public void delete(Long id) {
    devisRepository.deleteById(id);
  }
}
