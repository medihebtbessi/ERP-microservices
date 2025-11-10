package microservice.ERP.entreprise.equipe;

import microservice.ERP.entreprise.config.EquipeResponse;
import microservice.ERP.entreprise.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "equipe-server",
        url = "http://localhost:8025/api/equipes",
        configuration = FeignClientConfig.class
)
public interface EquipeClient {
    @GetMapping("/{id}")
    Optional<EquipeResponse> findEquipeById(@PathVariable("id") Long id );


}