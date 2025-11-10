package microservice.ERP.Equipe.Controller;

import jakarta.validation.Valid;
import microservice.ERP.Equipe.Entity.Equipe;
import microservice.ERP.Equipe.Service.EntrepriseService;
import microservice.ERP.Equipe.Service.EquipeService;
import microservice.ERP.Equipe.dto.EntrepriseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipes")
public class EquipeController {

    private final EquipeService equipeService;
    @Autowired
    EntrepriseService entrepriseService;

    public EquipeController(EquipeService equipeService) {
        this.equipeService = equipeService;
    }

    @GetMapping
    public Page<Equipe> getAll(@PageableDefault(size = 5, sort = "id") Pageable pageable) {
        return equipeService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Equipe getById(@PathVariable Long id) {
        return equipeService.getById(id);
    }

    @PostMapping
    public Equipe create(@Valid @RequestBody Equipe equipe) {
        return equipeService.save(equipe);
    }

    @PutMapping("/{id}")
    public Equipe update(@PathVariable Long id, @RequestBody Equipe equipe) {
        equipe.setId(id);
        return equipeService.save(equipe);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        equipeService.delete(id);
    }

    @GetMapping("/entreprises")
    public List<EntrepriseDTO> getEntreprises() {
        return entrepriseService.getAllEntreprises();
    }
}