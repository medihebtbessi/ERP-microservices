    package microservice.ERP.demo;

    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class EntrepriseService {

        @Autowired
        private EntrepriseRepo entrepriseRepo;

        @Autowired
        private ModelMapper modelMapper ;

        public Entreprise addEntreprise(Entreprise entreprise){
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
    }
