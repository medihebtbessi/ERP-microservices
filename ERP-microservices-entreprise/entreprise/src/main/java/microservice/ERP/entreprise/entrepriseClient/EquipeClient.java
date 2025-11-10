package microservice.ERP.entreprise.entrepriseClient;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "equipe-server", url = "http://localhost:8025")
public interface EquipeClient {
    @GetMapping("/api/equipes")
    EquipePageDTO  getAllEquipes();
}
