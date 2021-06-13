package arkham.knight.practica13.services;

import arkham.knight.practica13.models.Encuesta;
import arkham.knight.practica13.repositories.EncuestaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EncuestaService {

    private final EncuestaRepository encuestaRepo;

    public EncuestaService(EncuestaRepository encuestaRepo) {
        this.encuestaRepo = encuestaRepo;
    }


    public void crearEncuesta(Encuesta encuesta){

        encuestaRepo.save(encuesta);
    }

    public List<Encuesta> listarEncuestas(){

        return encuestaRepo.findAll();
    }
}
