package microservice.ERP.Financiere.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "facture")
public class Facture implements java.io.Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idFacture;

  @Column(nullable = false, unique = true)
  private String referenceFacture;

  @Column(nullable = false)
  private LocalDate dateEmission;

  private LocalDate dateEcheance;

  @Column(nullable = false)
  private Double montantTotal;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private StatutFacture statutFacture;

  // 🔹 Relation avec Devis (N factures → 1 devis)
  @ManyToOne
  @JoinColumn(name = "devis_id")
  @JsonBackReference
  private Devis devis;
  private Long idEntreprise;

  // --- Enum interne ---
  public enum StatutFacture {
    PAYEE,
    NON_PAYEE,
    PARTIELLE,
    ANNULEE
  }



  // === Getters & Setters ===
  public Long getIdFacture() {
    return idFacture;
  }

  public void setIdFacture(Long idFacture) {
    this.idFacture = idFacture;
  }

  public String getReferenceFacture() {
    return referenceFacture;
  }

  public void setReferenceFacture(String referenceFacture) {
    this.referenceFacture = referenceFacture;
  }

  public LocalDate getDateEmission() {
    return dateEmission;
  }

  public void setDateEmission(LocalDate dateEmission) {
    this.dateEmission = dateEmission;
  }

  public LocalDate getDateEcheance() {
    return dateEcheance;
  }

  public void setDateEcheance(LocalDate dateEcheance) {
    this.dateEcheance = dateEcheance;
  }

  public Double getMontantTotal() {
    return montantTotal;
  }

  public void setMontantTotal(Double montantTotal) {
    this.montantTotal = montantTotal;
  }

  public StatutFacture getStatutFacture() {
    return statutFacture;
  }

  public void setStatutFacture(StatutFacture statutFacture) {
    this.statutFacture = statutFacture;
  }

  public Devis getDevis() {
    return devis;
  }

  public void setDevis(Devis devis) {
    this.devis = devis;
  }
  public Long getIdEntreprise() { return idEntreprise; }
  public void setIdEntreprise(Long idEntreprise) { this.idEntreprise = idEntreprise; }
}
