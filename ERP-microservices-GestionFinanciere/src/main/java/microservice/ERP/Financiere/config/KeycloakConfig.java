package microservice.ERP.Financiere.config;

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
                .clientId("finance")
                .clientSecret("qAFsoKKRwG42bn5j4rbfa0c2ZN11GIXN")
                .build();
    }
}
