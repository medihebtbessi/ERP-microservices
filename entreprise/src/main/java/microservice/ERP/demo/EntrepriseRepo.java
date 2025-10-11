package microservice.ERP.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepo extends JpaRepository<Entreprise,Long> {
}
