package microservice.ERP.entreprise;

import jakarta.validation.GroupSequence;
import microservice.ERP.entreprise.client.ClientDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("entreprise")
public class EntrepriseController {


    @Autowired
    private EntrepriseService entrepriseService ;


    @GetMapping("helloEntreprise")
    public ResponseEntity<?> helloEntreprise(){
        return ResponseEntity.ok("hello entreprise");
    }

    @PostMapping("addEntreprise")
    public ResponseEntity<?> addEntreprise(@RequestBody Entreprise entreprise) {
        try {
            return ResponseEntity.ok(entrepriseService.addEntreprise(entreprise));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de l'ajout : " + e.getMessage());
        }
    }


    @GetMapping("getAllEntreprise")
    public ResponseEntity<?> getAllEntreprise(
            @RequestParam int page,
            @RequestParam int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        try {
            Page<Entreprise> entreprises = entrepriseService.getAllEntreprise(pageable);
            return ResponseEntity.ok(entreprises);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur : " + e.getMessage());
        }
    }

    @GetMapping("getEntrepriseById/{id}")
    public ResponseEntity<?> getEntrepriseById(@PathVariable Long id) {
        try {
            Entreprise entreprise = entrepriseService.getEntrepriseById(id);
            return ResponseEntity.ok(entreprise);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Entreprise non trouvée avec id : " + id);
        }
    }
    @PutMapping("updateEntreprise/{id}")
    public ResponseEntity<?> updateEntreprise(@PathVariable Long id, @RequestBody Entreprise entreprise) {
        try {
            Entreprise updated = entrepriseService.updateEntreprise(id, entreprise);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Erreur de mise à jour : " + e.getMessage());
        }
    }


    @DeleteMapping("deleteEntreprise/{id}")
    public ResponseEntity<?> deleteEntreprise(@PathVariable Long id) {
        try {
            entrepriseService.deleteEntreprise(id);
            return ResponseEntity.ok("Entreprise supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Erreur lors de la suppression : " + e.getMessage());
        }
    }
    @GetMapping("getAllClients")
    public List<ClientDto> getAllClients() {
        return entrepriseService.getAllClients();
    }



    @GetMapping("getClientById/{id}")
    public ClientDto getClientById(@PathVariable("id") String id){
        return entrepriseService.getClientById(id);
    }

    @PostMapping("affectedClientToEntreprise/{idEntreprise}/{idClient}")
    public ResponseEntity<?> affectedClientToEntreprise(
            @PathVariable Long  idEntreprise,
            @PathVariable String  idClient
            ) {
        try {
            return ResponseEntity.ok(entrepriseService.ajouterClientDansEntreprise(idEntreprise, idClient));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de l'ajout : " + e.getMessage());
        }
    }

}
