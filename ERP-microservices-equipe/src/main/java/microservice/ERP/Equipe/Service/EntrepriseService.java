package microservice.ERP.Equipe.Service;

import lombok.RequiredArgsConstructor;
import microservice.ERP.Equipe.config.EntrepriseClient;
import microservice.ERP.Equipe.dto.EntrepriseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Service
public class EntrepriseService {

    private final EntrepriseClient entrepriseClient;
    @Autowired
    public EntrepriseService(EntrepriseClient entrepriseClient) {
        this.entrepriseClient = entrepriseClient;
    }


    public List<EntrepriseDTO> getAllEntreprises() {
        Map<String, Object> response = entrepriseClient.getAllEntreprises(0, 50);
        return (List<EntrepriseDTO>) response.get("content");
    }
}
