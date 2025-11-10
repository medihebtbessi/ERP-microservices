package microservice.ERP.entreprise.clientConsume;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class ClientConsume {

    private final List<ClientResponse> clients = new ArrayList<>();


    @RabbitListener(queues = "client.test.q", containerFactory = "rabbitListenerContainerFactory")
    public void handleClientCreated(ClientEvent event) {
        ClientResponse client = event.data();
        clients.add(client);
        System.out.println("Received client: " + client.id() + ", " + client.nom());
    }


}
