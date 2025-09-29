package microservice.ERP.demo.repository;

import microservice.ERP.demo.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
}
