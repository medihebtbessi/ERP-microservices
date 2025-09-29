package microservice.ERP.demo.services;

import microservice.ERP.demo.model.Facture;
import microservice.ERP.demo.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FactureService {
  @Autowired
  private  FactureRepository factureRepository;



  public Page<Facture> getAll(Pageable pageable) {
    return factureRepository.findAll(pageable);
  }

  public Optional<Facture> getById(Long id) {
    return factureRepository.findById(id);
  }

  public Facture create(Facture facture) {
    return factureRepository.save(facture);
  }

  public Facture update(Long id, Facture facture) {
    facture.setIdFacture(id);
    return factureRepository.save(facture);
  }

  public void delete(Long id) {
    factureRepository.deleteById(id);
  }
}
