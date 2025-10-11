package microservice.ERP.entreprise;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepo extends JpaRepository<Entreprise,Long> {
}
