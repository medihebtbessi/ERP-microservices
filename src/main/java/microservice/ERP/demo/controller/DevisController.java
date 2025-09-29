package microservice.ERP.demo.controller;

import lombok.RequiredArgsConstructor;
import microservice.ERP.demo.model.Devis;
import microservice.ERP.demo.services.DevisService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devis")
@CrossOrigin(origins = "http://localhost:4200")
public class DevisController {

  private final DevisService devisService;

  public DevisController(DevisService devisService) {
    this.devisService = devisService;
  }

  @GetMapping()
  public Page<Devis> getAll(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size) {
    return devisService.getAll(PageRequest.of(page, size));
  }

  @GetMapping("/{id}")
  public Devis getById(@PathVariable Long id) {
    return devisService.getById(id).orElse(null);
  }

  @PostMapping
  public Devis create(@RequestBody Devis devis) {
    return devisService.create(devis);
  }

  @PutMapping("/{id}")
  public Devis update(@PathVariable Long id, @RequestBody Devis devis) {
    return devisService.update(id, devis);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    devisService.delete(id);
  }
}
