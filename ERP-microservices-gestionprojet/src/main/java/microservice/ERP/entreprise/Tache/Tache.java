package microservice.ERP.entreprise.Tache;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.ERP.entreprise.Projet.Projet;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    @Enumerated(EnumType.STRING)
    private StatusTache status;

    @Enumerated(EnumType.STRING)
    private PrioriteTache priorite;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;
}
