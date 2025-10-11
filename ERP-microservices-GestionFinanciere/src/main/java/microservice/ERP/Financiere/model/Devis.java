package microservice.ERP.Financiere.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "devis")
public class Devis {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idDevis;

  @Column(nullable = false, unique = true)
  private String referenceDevis;

  @Column(nullable = false)
  private LocalDate dateCreation;

  private LocalDate dateExpiration;

  @Column(nullable = false)
  private Double montantTotal;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private StatutDevis statutDevis;

  @OneToMany(mappedBy = "devis", cascade = CascadeType.ALL)
  @JsonManagedReference
  private List<Facture> factures;

  // --- Enum interne ---
  public enum StatutDevis {
    EN_ATTENTE,
    VALIDE,
    REFUSE,
    EXPIRE
  }

  // === Getters & Setters ===
  public Long getIdDevis() {
    return idDevis;
  }

  public void setIdDevis(Long idDevis) {
    this.idDevis = idDevis;
  }

  public String getReferenceDevis() {
    return referenceDevis;
  }

  public void setReferenceDevis(String referenceDevis) {
    this.referenceDevis = referenceDevis;
  }

  public LocalDate getDateCreation() {
    return dateCreation;
  }

  public void setDateCreation(LocalDate dateCreation) {
    this.dateCreation = dateCreation;
  }

  public LocalDate getDateExpiration() {
    return dateExpiration;
  }

  public void setDateExpiration(LocalDate dateExpiration) {
    this.dateExpiration = dateExpiration;
  }

  public Double getMontantTotal() {
    return montantTotal;
  }

  public void setMontantTotal(Double montantTotal) {
    this.montantTotal = montantTotal;
  }

  public StatutDevis getStatutDevis() {
    return statutDevis;
  }

  public void setStatutDevis(StatutDevis statutDevis) {
    this.statutDevis = statutDevis;
  }

  public List<Facture> getFactures() {
    return factures;
  }

  public void setFactures(List<Facture> factures) {
    this.factures = factures;
  }
}
