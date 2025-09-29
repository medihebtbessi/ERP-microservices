package microservice.ERP.demo.controller;

import microservice.ERP.demo.model.Devis;
import microservice.ERP.demo.model.Facture;
import microservice.ERP.demo.services.FactureService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
@CrossOrigin(origins = "http://localhost:4200")
public class FactureController {

  private final FactureService factureService;

  public FactureController(FactureService factureService) {
    this.factureService = factureService;
  }

  @GetMapping
  public Page<Facture> getAll(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size) {
    return factureService.getAll(PageRequest.of(page, size));
  }

  @GetMapping("/{id}")
  public Facture getById(@PathVariable Long id) {
    return factureService.getById(id).orElse(null);
  }

  @PostMapping
  public Facture create(@RequestBody Facture facture) {
    return factureService.create(facture);
  }

  @PutMapping("/{id}")
  public Facture update(@PathVariable Long id, @RequestBody Facture facture) {
    return factureService.update(id, facture);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    factureService.delete(id);
  }
}
