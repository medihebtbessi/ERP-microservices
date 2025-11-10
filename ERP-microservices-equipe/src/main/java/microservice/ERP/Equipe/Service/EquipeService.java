package microservice.ERP.Equipe.Service;


import microservice.ERP.Equipe.Entity.Equipe;
import microservice.ERP.Equipe.Repository.EquipeRepository;
import microservice.ERP.Equipe.config.EquipeProducer;
import microservice.ERP.Equipe.config.EquipeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EquipeService {
    private final EquipeRepository equipeRepository;
    private final EquipeProducer equipeProducer;

    public EquipeService(EquipeRepository equipeRepository, EquipeProducer equipeProducer ) {
        this.equipeRepository = equipeRepository;
        this.equipeProducer= equipeProducer;
    }

    public Page<Equipe> getAll(Pageable pageable) {
        return equipeRepository.findAll(pageable);
    }

    public Equipe getById(Long id) {
        return equipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipe non trouvée"));
    }

    public Equipe save(Equipe equipe) {
        equipe=equipeRepository.save(equipe);
        EquipeResponse equipeResponse = new EquipeResponse(equipe.getId(), equipe.getNom());
        equipeProducer.sendProjectCreatedEvent(equipeResponse);
        return equipe;
    }

    public void delete(Long id) {
        equipeRepository.deleteById(id);
    }
}
