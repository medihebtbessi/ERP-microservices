package microservice.ERP.entreprise;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {
    @Bean
    public Keycloak keycloakAdminClient(){
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:9098")
                .realm("erp-bh")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("projet")
                .clientSecret("Sm0FzV7JJgp97GE5Pbg5C9rKsH5436SW")
                .build();
    }

}
