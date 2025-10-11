package microservice.ERP.demo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id ;
    private String name ;
    private String raisonSociale;
    private String description;
    private String secteurActivite;
    private String statutJuridique;
    private String numeroIdentificationFiscale;
    private String registreCommerce;
    private String siteWeb;
    private LocalDate dateCreation;
    private LocalDateTime dateDerniereModification;
    private String email ;
    private String adresse ;
    private String Telephone ;
    @Enumerated(EnumType.STRING)
    private StatutEntreprise statut;

    @Enumerated(EnumType.STRING)
    private CategorieEntreprise categorie;




}
