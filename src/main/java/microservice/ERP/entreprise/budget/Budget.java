package microservice.ERP.entreprise.budget;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import microservice.ERP.entreprise.depense.Depense;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private Double montant;

    private LocalDate dateDebut;

    private LocalDate dateFin;


    @Column(unique = true)
    private Long idProjet ;


    @OneToMany(mappedBy = "budget")
    @JsonIgnoreProperties(value = "budget")
    private List<Depense> depenses ;

}