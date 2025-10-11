package microservice.ERP.demo.Tache;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taches")
@RequiredArgsConstructor
public class TacheController {

    private final TacheService tacheService;

    @PostMapping("/projet/{projetId}")
    public Tache createTache(@PathVariable Long projetId, @RequestBody Tache tache) {
        return tacheService.createTache(projetId, tache);
    }

    @GetMapping("/projet/{projetId}")
    public List<Tache> getTachesByProjet(@PathVariable Long projetId) {
        return tacheService.getTachesByProjet(projetId);
    }

    @PutMapping("/{id}")
    public Tache updateTache(@PathVariable Long id, @RequestBody Tache tache) {
        return tacheService.updateTache(id, tache);
    }

    @DeleteMapping("/{id}")
    public void deleteTache(@PathVariable Long id) {
        tacheService.deleteTache(id);
    }

}
