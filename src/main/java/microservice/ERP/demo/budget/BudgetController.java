package microservice.ERP.demo.budget;

import lombok.RequiredArgsConstructor;
import microservice.ERP.demo.common.PageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/budget")
@CrossOrigin(origins = "http://localhost:4200")
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping
    public ResponseEntity<Long> createBudget(@RequestBody Budget budget) {
        Long id = budgetService.createBudget(budget);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
        return ResponseEntity.ok(budgetService.getBudgetById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<Budget>> getAllBudgets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(budgetService.getAllBudgets(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateBudget(
            @PathVariable Long id,
            @RequestBody Budget budget
    ) {
        Long updatedId = budgetService.updateBudget(id, budget);
        return ResponseEntity.ok(updatedId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.noContent().build();
    }
}
