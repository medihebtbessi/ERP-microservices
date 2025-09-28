package microservice.ERP.demo.budget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BudgetRepo extends JpaRepository<Budget,Long>, JpaSpecificationExecutor<Budget> {
}
