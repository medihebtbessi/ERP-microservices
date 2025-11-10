package microservice.ERP.entreprise;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntrepriseDTO {
    private Long id;
    private String name;
    private String secteurActivite;
    private String adresse;
}

