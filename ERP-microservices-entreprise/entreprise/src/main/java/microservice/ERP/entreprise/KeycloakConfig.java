package microservice.ERP.entreprise;



import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class KeycloakConfig {

   // @Bean
    public Keycloak keycloakAdminClient(){
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:9098")
                .realm("erp-bh")
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId("entreprise")
                .clientSecret("FENpjcNQU4pR6Vj2JCbwfggMoBQPkyp7")
                .build();
    }
}
