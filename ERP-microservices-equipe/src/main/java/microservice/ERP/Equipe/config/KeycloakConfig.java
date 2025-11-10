package microservice.ERP.Equipe.config;

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
                .clientId("equipe")
                .clientSecret("iJDZ9vzoeVw5d2fC3zPKH25u6zIYM0X6")
                .build();
    }
}
