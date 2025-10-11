package microservice.ERP.Equipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class EquipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquipeApplication.class, args);
	}

}
