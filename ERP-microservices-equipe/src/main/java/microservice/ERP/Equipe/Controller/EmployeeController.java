package microservice.ERP.Equipe.Controller;

import lombok.RequiredArgsConstructor;
import microservice.ERP.Equipe.Entity.Employee;
import microservice.ERP.Equipe.Entity.Equipe;
import microservice.ERP.Equipe.Repository.EmployeeRepository;
import microservice.ERP.Equipe.Repository.EquipeRepository;
import microservice.ERP.Equipe.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
//CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EquipeRepository equipeRepository;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee updatedEmployee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, updatedEmployee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<Employee> updateEmployeeStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> statusUpdate) {

        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            String newStatus = statusUpdate.get("status");

            // Validation du statut
            if (isValidStatus(newStatus)) {
                employee.setStatus(newStatus);
                Employee updatedEmployee = employeeRepository.save(employee);
                return ResponseEntity.ok(updatedEmployee);
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.notFound().build();
    }
    @GetMapping("/status/{status}")
    public List<Employee> getEmployeesByStatus(@PathVariable String status) {
        return employeeRepository.findByStatus(status);
    }

    // 🆕 GET - Nombre d'employés actifs
    @GetMapping("/count/active")
    public ResponseEntity<Long> getActiveEmployeesCount() {
        long count = employeeRepository.countByStatus("active");
        return ResponseEntity.ok(count);
    }

    // 🆕 GET - Statistiques par statut
    @GetMapping("/stats/status")
    public ResponseEntity<Map<String, Long>> getStatusStatistics() {
        Map<String, Long> stats = Map.of(
                "active", employeeRepository.countByStatus("active"),
                "inactive", employeeRepository.countByStatus("inactive"),
                "on-leave", employeeRepository.countByStatus("on-leave"),
                "terminated", employeeRepository.countByStatus("terminated")
        );
        return ResponseEntity.ok(stats);
    }

    // Méthode de validation du statut
    private boolean isValidStatus(String status) {
        return status != null &&
                (status.equals("active") ||
                        status.equals("inactive") ||
                        status.equals("on-leave") ||
                        status.equals("terminated"));
    }
    @PostMapping("/with-equipe")
    public ResponseEntity<?> createEmployeeWithEquipe(@RequestBody Employee employee) {
        Equipe equipe = equipeRepository.findById(employee.getEquipe().getId())
                .orElseThrow(() -> new RuntimeException("Equipe introuvable"));

        employee.setEquipe(equipe);
        employeeRepository.save(employee);
        return ResponseEntity.ok().build();
    }
}
