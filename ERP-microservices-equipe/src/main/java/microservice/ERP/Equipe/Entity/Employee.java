package microservice.ERP.Equipe.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String id;
    private String employeeName;
    private String employeeCompany;
    private LocalDate joiningDate;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String department;
    private String designation;
    private String description;
    private List<String> permissions;
    private String project;

}

