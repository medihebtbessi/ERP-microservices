package microservice.ERP.entreprise.clientConsume;

public record ClientEvent(
        String eventId,
        String type,
        String source,
        String timestamp,
        ClientResponse data
) {}