package microservice.ERP.Financiere.client;

import microservice.ERP.Financiere.client.dto.EntrepriseResponse;
import microservice.ERP.Financiere.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "entreprise-server",url = "http://localhost:8021/entreprise",        configuration = FeignClientConfig.class
)
public interface EntrepriseClient {
  @GetMapping("/getEntrepriseById/{id}")
  EntrepriseResponse getById(@PathVariable("id") Long id);;
}
