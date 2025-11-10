package microservice.ERP.Equipe.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // ✅ auto-génère l'ID
    private String id;

    private String employeeName;
    private String employeeCompany;

    @JsonFormat(pattern = "yyyy-MM-dd") // ✅ pour bien parser la date
    private LocalDate joiningDate;

    private String email;
    private String phone;
    private String department;
    private String designation;
    private String description;
    private String status = "active";

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}