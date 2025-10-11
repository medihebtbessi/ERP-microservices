package microservice.ERP.entreprise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class BudgetManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetManagementApplication.class, args);
	}

}
