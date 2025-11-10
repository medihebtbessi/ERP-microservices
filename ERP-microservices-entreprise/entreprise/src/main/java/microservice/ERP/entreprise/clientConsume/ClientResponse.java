package microservice.ERP.entreprise.clientConsume;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClientResponse(
        String id,
        String nom,
        String prenom,
        String email,
        String statut
) {}

