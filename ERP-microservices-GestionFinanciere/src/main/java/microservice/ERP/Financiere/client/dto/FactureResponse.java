package microservice.ERP.Financiere.client.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class FactureResponse {
    private Long idFacture;
    private String referenceFacture;
    private LocalDate dateEmission;
    private LocalDate dateEcheance;
    private Double montantTotal;
    private String statutFacture;

    private String entrepriseName; // ✅ nom client
    private String referenceDevis; // ✅ utile pour affichage
}