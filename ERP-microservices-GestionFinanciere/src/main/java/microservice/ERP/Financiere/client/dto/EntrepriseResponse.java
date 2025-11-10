package microservice.ERP.Financiere.client.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
public class EntrepriseResponse{
  private Long id;
  private String name;
  private String adresse;
  private String email;

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAdresse(String adresse) {
    this.adresse = adresse;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAdresse() {
    return adresse;
  }

  public String getEmail() {
    return email;
  }
}


