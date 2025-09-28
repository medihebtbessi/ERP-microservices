package microservice.ERP.demo.depense;

import microservice.ERP.demo.budget.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepenseRepo extends JpaRepository<Depense,Long> , JpaSpecificationExecutor<Depense> {
    Page<Depense> findByBudget(Budget budget, Pageable pageable);
}
