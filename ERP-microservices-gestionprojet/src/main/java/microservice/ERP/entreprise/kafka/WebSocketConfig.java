package microservice.ERP.entreprise.kafka;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Le broker en mémoire qui envoie les messages vers le client
        config.enableSimpleBroker("/topic");
        // Les préfixes utilisés par le frontend pour envoyer des messages
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/api/v1/ws")
                // Autoriser à la fois Angular (4200) et Gateway (8222)
                .setAllowedOriginPatterns("http://localhost:4200", "http://localhost:8222")
                .withSockJS(); // Supporte SockJS (fallback XHR)
    }
}
