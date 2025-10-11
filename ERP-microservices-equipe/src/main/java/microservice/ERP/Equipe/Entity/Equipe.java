package microservice.ERP.Equipe.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Equipe {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Le nom est obligatoire")
        private String nom;

        @NotBlank(message = "La description est obligatoire")
        private String description;

        @Enumerated(EnumType.STRING)
        @NotNull(message = "La catégorie est obligatoire")
        private CategorieEquipe categorie;

        @Enumerated(EnumType.STRING)
        @NotNull(message = "Le statut est obligatoire")
        private AvailabilityStatus availabilityStatus;

     public Equipe() {}
    public Equipe(String nom, String description, CategorieEquipe categorie, AvailabilityStatus status) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.availabilityStatus = status;
    }

     public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public CategorieEquipe getCategorie() { return categorie; }
    public void setCategorie(CategorieEquipe categorie) { this.categorie = categorie; }

    public AvailabilityStatus getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) { this.availabilityStatus = availabilityStatus; }
}