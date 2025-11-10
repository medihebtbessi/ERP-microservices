package microservice.ERP.entreprise.client;


import microservice.ERP.entreprise.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "client-service", url = "http://localhost:8026",configuration = FeignClientConfig.class)
public interface ClientEntreprise {

    @GetMapping("/api/clients/getAll")
    List<ClientDto> getAllClients();

    @GetMapping("/api/clients/{_id}")
    ClientDto getClientById(@PathVariable("_id") String id);
}