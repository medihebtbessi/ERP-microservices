package microservice.ERP.Equipe.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrepriseDTO {
    private Long id;
    private String name;
    private String secteurActivite;
    private String adresse;
}