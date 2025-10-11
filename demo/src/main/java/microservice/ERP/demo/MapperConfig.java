package microservice.ERP.demo;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.typeMap(Entreprise.class, Entreprise.class)
                .addMappings(m -> m.skip(Entreprise::setId));
        return mapper;
    }
}