package microservice.ERP.demo.depense;

import lombok.RequiredArgsConstructor;
import microservice.ERP.demo.common.PageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/depense")
@CrossOrigin(origins = "http://localhost:4200")
public class DepenseController {

    private final DepenseService depenseService;

    @PostMapping
    public ResponseEntity<Long> createDepense(@RequestBody Depense depense) {
        Long id = depenseService.createDepense(depense);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Depense> getDepenseById(@PathVariable Long id) {
        return ResponseEntity.ok(depenseService.getDepenseById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<Depense>> getAllDepenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(depenseService.getAllDepenses(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateDepense(
            @PathVariable Long id,
            @RequestBody Depense depense
    ) {
        Long updatedId = depenseService.updateDepense(id, depense);
        return ResponseEntity.ok(updatedId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepense(@PathVariable Long id) {
        depenseService.deleteDepense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/budget/{budgetId}")
    public ResponseEntity<PageResponse<Depense>> getDepensesByBudget(
            @PathVariable Long budgetId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(depenseService.getDepensesByBudget(budgetId, page, size));
    }

    @GetMapping("/budget/{budgetId}/total")
    public ResponseEntity<Double> getTotalDepensesByBudget(@PathVariable Long budgetId) {
        return ResponseEntity.ok(depenseService.getTotalDepensesByBudget(budgetId));
    }


}
