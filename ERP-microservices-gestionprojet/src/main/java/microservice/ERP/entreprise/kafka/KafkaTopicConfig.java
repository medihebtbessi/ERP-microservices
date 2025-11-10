package microservice.ERP.entreprise.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic budgetAlertTopic() {
        return new NewTopic("project-alert", 1, (short) 1);
    }
}