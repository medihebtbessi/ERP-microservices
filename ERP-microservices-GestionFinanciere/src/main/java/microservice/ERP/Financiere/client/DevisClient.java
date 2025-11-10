package microservice.ERP.Financiere.client;

import microservice.ERP.Financiere.client.dto.DevisResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "devis-server", url = "http://localhost:8023/api/devis")
public interface DevisClient {

  @GetMapping("/{id}")
  DevisResponse getById(@PathVariable("id") Long id);
}
