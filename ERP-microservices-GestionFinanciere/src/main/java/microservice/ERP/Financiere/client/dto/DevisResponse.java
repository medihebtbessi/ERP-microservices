package microservice.ERP.Financiere.client.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DevisResponse {

    private Long idDevis;
    private String referenceDevis;
    private Double montantTotal;

    private LocalDate dateCreation;
    private LocalDate dateExpiration;

    private String statutDevis;     // toString from Enum
    private String entrepriseName;  // récupérée depuis microservice Entreprise
}