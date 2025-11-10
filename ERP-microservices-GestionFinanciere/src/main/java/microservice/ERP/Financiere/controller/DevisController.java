package microservice.ERP.Financiere.controller;

import microservice.ERP.Financiere.client.EntrepriseClient;
import microservice.ERP.Financiere.client.dto.DevisResponse;
import microservice.ERP.Financiere.client.dto.EntrepriseResponse;
import microservice.ERP.Financiere.model.Devis;
import microservice.ERP.Financiere.services.DevisService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devis")
public class DevisController {

  private final DevisService devisService;
 private final EntrepriseClient entrepriseClient;
  public DevisController(DevisService devisService , EntrepriseClient entrepriseClient) {
    this.devisService = devisService;
    this.entrepriseClient = entrepriseClient;
  }

  @GetMapping()
  public Page<DevisResponse> getAll(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size) {
    return devisService.getAll(PageRequest.of(page, size));
  }

  @GetMapping("/{id}")
  public Devis getById(@PathVariable Long id) {
    return devisService.getById(id).orElse(null);
  }

  @PostMapping
  public DevisResponse create(@RequestBody Devis devis) {
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


  @GetMapping(value = "/test-entreprise/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EntrepriseResponse> testEntreprise(@PathVariable Long id) {
    EntrepriseResponse entreprise = entrepriseClient.getById(id);
    return ResponseEntity
      .ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(entreprise);
  }
}
