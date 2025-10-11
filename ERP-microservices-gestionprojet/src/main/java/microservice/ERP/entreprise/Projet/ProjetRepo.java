package microservice.ERP.entreprise.Projet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProjetRepo extends JpaRepository<Projet,Long>, JpaSpecificationExecutor<Projet> {
}
