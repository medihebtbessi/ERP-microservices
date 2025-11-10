package microservice.ERP.Equipe.config;

import microservice.ERP.Equipe.dto.EntrepriseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(
        name = "entreprise-server",
        url = "http://localhost:8021/",
        configuration = FeignClientConfig.class
)
public interface EntrepriseClient {

    @GetMapping("/entreprise/getEntrepriseById/{id}")
    EntrepriseDTO findById(@PathVariable("id") Long id);

    @GetMapping("/entreprise/getAllEntreprise")
    Map<String, Object> getAllEntreprises(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    );
}