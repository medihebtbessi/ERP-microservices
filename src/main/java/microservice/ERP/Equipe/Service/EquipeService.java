package microservice.ERP.Equipe.Service;


import microservice.ERP.Equipe.Entity.Equipe;
import microservice.ERP.Equipe.Repository.EquipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EquipeService {
    private final EquipeRepository equipeRepository;

    public EquipeService(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    public Page<Equipe> getAll(Pageable pageable) {
        return equipeRepository.findAll(pageable);
    }

    public Equipe getById(Long id) {
        return equipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipe non trouvée"));
    }

    public Equipe save(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    public void delete(Long id) {
        equipeRepository.deleteById(id);
    }
}
