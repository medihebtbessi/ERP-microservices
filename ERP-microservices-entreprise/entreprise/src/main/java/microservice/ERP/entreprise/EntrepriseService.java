    package microservice.ERP.entreprise;

    import lombok.RequiredArgsConstructor;
    import microservice.ERP.entreprise.client.ClientDto;
    import microservice.ERP.entreprise.client.ClientEntreprise;
    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Service;
    import microservice.ERP.entreprise.config.Entrepriseproducer;


    import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class EntrepriseService {


        private final EntrepriseRepo entrepriseRepo;


        private final ModelMapper modelMapper ;

        private final ClientEntreprise clientEntreprise ;

        private final Entrepriseproducer entrepriseproducer;


        public Entreprise addEntreprise(Entreprise entreprise){
                    entrepriseproducer.sendProjectCreatedEvent(entreprise);

           return entrepriseRepo.save(entreprise);
        }
        public Page<Entreprise> getAllEntreprise(Pageable pageable){
            return entrepriseRepo.findAll(pageable);
        }
        public Entreprise getEntrepriseById(Long id){
            return entrepriseRepo.findById(id).get();
        }
        public void deleteEntreprise(Long id){
            entrepriseRepo.deleteById(id);
        }
        public Entreprise updateEntreprise(Long idEntreprise,Entreprise entreprise){
            Entreprise existing = entrepriseRepo.findById(idEntreprise)
                    .orElseThrow(() -> new RuntimeException("Entreprise non trouvée avec id : " + idEntreprise));
            modelMapper.map(entreprise,existing);
            return entrepriseRepo.save(existing);
        }


        public List<ClientDto> getAllClients() {
            return  clientEntreprise.getAllClients();
        }

        public ClientDto getClientById(String id) {
                    return clientEntreprise.getClientById(id);
        }


        public String ajouterClientDansEntreprise(Long idEntreprise,String idClient){
            Entreprise existing = entrepriseRepo.findById(idEntreprise)
                    .orElseThrow(() -> new RuntimeException("Entreprise non trouvée avec id : " + idEntreprise));

            ClientDto clientDto = clientEntreprise.getClientById(idClient);
            if (clientDto==null){
                throw  new RuntimeException("Client non trouvée");
            }
            existing.getIdClient().add(clientDto.getId());
            entrepriseRepo.save(existing);
            return "client Affected To Entreprise";
        }
    }
