package microservice.ERP.Equipe.Repository;

import microservice.ERP.Equipe.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByEmail(String email);

    List<Employee> findByStatus(String status);

    long countByStatus(String status);

    List<Employee> findByDepartmentAndStatus(String department, String status);

    List<Employee> findByEmployeeNameContainingIgnoreCase(String name);
}
