package microservice.ERP.entreprise.budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BudgetRepo extends JpaRepository<Budget,Long>, JpaSpecificationExecutor<Budget> {
}
