package microservice.ERP.demo.Tache;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepo extends JpaRepository<Tache, Long> {
    List<Tache> findByProjetId(Long projetId);
}
